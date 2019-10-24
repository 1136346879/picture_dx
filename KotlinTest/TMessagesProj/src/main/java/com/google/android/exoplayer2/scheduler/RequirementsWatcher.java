/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.scheduler;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

/**
 * Watches whether the {@link Requirements} are met and notifies the {@link Listener} on changes.
 */
public final class RequirementsWatcher {

  /**
   * Notified when RequirementsWatcher instance first created and on changes whether the {@link
   * Requirements} are met.
   */
  public interface Listener {

    /**
     * Called when all of the requirements are met.
     *
     * @param requirementsWatcher Calling instance.
     */
    void requirementsMet(RequirementsWatcher requirementsWatcher);

    /**
     * Called when there is at least one not met requirement and there is a change on which of the
     * requirements are not met.
     *
     * @param requirementsWatcher Calling instance.
     * @param notMetRequirements {@link Requirements.RequirementFlags RequirementFlags} that are not
     *     met, or 0.
     */
    void requirementsNotMet(
        RequirementsWatcher requirementsWatcher,
        @Requirements.RequirementFlags int notMetRequirements);
  }

  private static final String TAG = "RequirementsWatcher";

  private final Context context;
  private final Listener listener;
  private final Requirements requirements;
  private DeviceStatusChangeReceiver receiver;

  @Requirements.RequirementFlags private int notMetRequirements;
  private CapabilityValidatedCallback networkCallback;
  private Handler handler;

  /**
   * @param context Any context.
   * @param listener Notified whether the {@link Requirements} are met.
   * @param requirements The requirements to watch.
   */
  public RequirementsWatcher(Context context, Listener listener, Requirements requirements) {
    this.requirements = requirements;
    this.listener = listener;
    this.context = context.getApplicationContext();
    logd(this + " created");
  }

  /**
   * Starts watching for changes. Must be called from a thread that has an associated {@link
   * Looper}. Listener methods are called on the caller thread.
   *
   * @return Initial {@link Requirements.RequirementFlags RequirementFlags} that are not met, or 0.
   */
  @Requirements.RequirementFlags
  public int start() {
    Assertions.checkNotNull(Looper.myLooper());
    handler = new Handler();

    notMetRequirements = requirements.getNotMetRequirements(context);

    IntentFilter filter = new IntentFilter();
    if (requirements.getRequiredNetworkType() != Requirements.NETWORK_TYPE_NONE) {
      if (Util.SDK_INT >= 23) {
        registerNetworkCallbackV23();
      } else {
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
      }
    }
    if (requirements.isChargingRequired()) {
      filter.addAction(Intent.ACTION_POWER_CONNECTED);
      filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    }
    if (requirements.isIdleRequired()) {
      if (Util.SDK_INT >= 23) {
        filter.addAction(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);
      } else {
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
      }
    }
    receiver = new DeviceStatusChangeReceiver();
    context.registerReceiver(receiver, filter, null, handler);
    logd(this + " started");
    return notMetRequirements;
  }

  /** Stops watching for changes. */
  public void stop() {
    context.unregisterReceiver(receiver);
    receiver = null;
    if (networkCallback != null) {
      unregisterNetworkCallback();
    }
    logd(this + " stopped");
  }

  /** Returns watched {@link Requirements}. */
  public Requirements getRequirements() {
    return requirements;
  }

  @Override
  public String toString() {
    if (!Scheduler.DEBUG) {
      return super.toString();
    }
    return "RequirementsWatcher{" + requirements + '}';
  }

  @TargetApi(23)
  private void registerNetworkCallbackV23() {
    ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkRequest request =
        new NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            .build();
    networkCallback = new CapabilityValidatedCallback();
    connectivityManager.registerNetworkCallback(request, networkCallback);
  }

  private void unregisterNetworkCallback() {
    if (Util.SDK_INT >= 21) {
      ConnectivityManager connectivityManager =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      connectivityManager.unregisterNetworkCallback(networkCallback);
      networkCallback = null;
    }
  }

  private void checkRequirements() {
    @Requirements.RequirementFlags
    int notMetRequirements = requirements.getNotMetRequirements(context);
    if (this.notMetRequirements == notMetRequirements) {
      logd("notMetRequirements hasn't changed: " + notMetRequirements);
      return;
    }
    this.notMetRequirements = notMetRequirements;
    if (notMetRequirements == 0) {
      logd("start job");
      listener.requirementsMet(this);
    } else {
      logd("stop job");
      listener.requirementsNotMet(this, notMetRequirements);
    }
  }

  private static void logd(String message) {
    if (Scheduler.DEBUG) {
      Log.d(TAG, message);
    }
  }

  private class DeviceStatusChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      if (!isInitialStickyBroadcast()) {
        logd(RequirementsWatcher.this + " received " + intent.getAction());
        checkRequirements();
      }
    }
  }

  @RequiresApi(api = 21)
  private final class CapabilityValidatedCallback extends ConnectivityManager.NetworkCallback {
    @Override
    public void onAvailable(Network network) {
      onNetworkCallback();
    }

    @Override
    public void onLost(Network network) {
      onNetworkCallback();
    }

    private void onNetworkCallback() {
      handler.post(
          () -> {
            if (networkCallback != null) {
              logd(RequirementsWatcher.this + " NetworkCallback");
              checkRequirements();
            }
          });
    }
  }
}
