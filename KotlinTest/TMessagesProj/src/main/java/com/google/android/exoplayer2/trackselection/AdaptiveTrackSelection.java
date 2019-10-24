/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.google.android.exoplayer2.trackselection;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableType;

/**
 * A bandwidth based adaptive {@link TrackSelection}, whose selected track is updated to be the one
 * of highest quality given the current network conditions and the state of the buffer.
 */
public class AdaptiveTrackSelection extends BaseTrackSelection {

  /**
   * Factory for {@link AdaptiveTrackSelection} instances.
   */
  public static final class Factory implements TrackSelection.Factory {

    private final @Nullable BandwidthMeter bandwidthMeter;
    private final int minDurationForQualityIncreaseMs;
    private final int maxDurationForQualityDecreaseMs;
    private final int minDurationToRetainAfterDiscardMs;
    private final float bandwidthFraction;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final long minTimeBetweenBufferReevaluationMs;
    private final Clock clock;

    private TrackBitrateEstimator trackBitrateEstimator;
    private boolean blockFixedTrackSelectionBandwidth;

    /** Creates an adaptive track selection factory with default parameters. */
    public Factory() {
      this(
          DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS,
          DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS,
          DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS,
          DEFAULT_BANDWIDTH_FRACTION,
          DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE,
          DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS,
          Clock.DEFAULT);
    }

    /**
     * @deprecated Use {@link #Factory()} instead. Custom bandwidth meter should be directly passed
     *     to the player in {@link ExoPlayerFactory}.
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Factory(BandwidthMeter bandwidthMeter) {
      this(
          bandwidthMeter,
          DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS,
          DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS,
          DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS,
          DEFAULT_BANDWIDTH_FRACTION,
          DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE,
          DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS,
          Clock.DEFAULT);
    }

    /**
     * Creates an adaptive track selection factory.
     *
     * @param minDurationForQualityIncreaseMs The minimum duration of buffered data required for the
     *     selected track to switch to one of higher quality.
     * @param maxDurationForQualityDecreaseMs The maximum duration of buffered data required for the
     *     selected track to switch to one of lower quality.
     * @param minDurationToRetainAfterDiscardMs When switching to a track of significantly higher
     *     quality, the selection may indicate that media already buffered at the lower quality can
     *     be discarded to speed up the switch. This is the minimum duration of media that must be
     *     retained at the lower quality.
     * @param bandwidthFraction The fraction of the available bandwidth that the selection should
     *     consider available for use. Setting to a value less than 1 is recommended to account for
     *     inaccuracies in the bandwidth estimator.
     */
    public Factory(
        int minDurationForQualityIncreaseMs,
        int maxDurationForQualityDecreaseMs,
        int minDurationToRetainAfterDiscardMs,
        float bandwidthFraction) {
      this(
          minDurationForQualityIncreaseMs,
          maxDurationForQualityDecreaseMs,
          minDurationToRetainAfterDiscardMs,
          bandwidthFraction,
          DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE,
          DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS,
          Clock.DEFAULT);
    }

    /**
     * @deprecated Use {@link #Factory(int, int, int, float)} instead. Custom bandwidth meter should
     *     be directly passed to the player in {@link ExoPlayerFactory}.
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Factory(
        BandwidthMeter bandwidthMeter,
        int minDurationForQualityIncreaseMs,
        int maxDurationForQualityDecreaseMs,
        int minDurationToRetainAfterDiscardMs,
        float bandwidthFraction) {
      this(
          bandwidthMeter,
          minDurationForQualityIncreaseMs,
          maxDurationForQualityDecreaseMs,
          minDurationToRetainAfterDiscardMs,
          bandwidthFraction,
          DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE,
          DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS,
          Clock.DEFAULT);
    }

    /**
     * Creates an adaptive track selection factory.
     *
     * @param minDurationForQualityIncreaseMs The minimum duration of buffered data required for the
     *     selected track to switch to one of higher quality.
     * @param maxDurationForQualityDecreaseMs The maximum duration of buffered data required for the
     *     selected track to switch to one of lower quality.
     * @param minDurationToRetainAfterDiscardMs When switching to a track of significantly higher
     *     quality, the selection may indicate that media already buffered at the lower quality can
     *     be discarded to speed up the switch. This is the minimum duration of media that must be
     *     retained at the lower quality.
     * @param bandwidthFraction The fraction of the available bandwidth that the selection should
     *     consider available for use. Setting to a value less than 1 is recommended to account for
     *     inaccuracies in the bandwidth estimator.
     * @param bufferedFractionToLiveEdgeForQualityIncrease For live streaming, the fraction of the
     *     duration from current playback position to the live edge that has to be buffered before
     *     the selected track can be switched to one of higher quality. This parameter is only
     *     applied when the playback position is closer to the live edge than {@code
     *     minDurationForQualityIncreaseMs}, which would otherwise prevent switching to a higher
     *     quality from happening.
     * @param minTimeBetweenBufferReevaluationMs The track selection may periodically reevaluate its
     *     buffer and discard some chunks of lower quality to improve the playback quality if
     *     network conditions have changed. This is the minimum duration between 2 consecutive
     *     buffer reevaluation calls.
     * @param clock A {@link Clock}.
     */
    @SuppressWarnings("deprecation")
    public Factory(
        int minDurationForQualityIncreaseMs,
        int maxDurationForQualityDecreaseMs,
        int minDurationToRetainAfterDiscardMs,
        float bandwidthFraction,
        float bufferedFractionToLiveEdgeForQualityIncrease,
        long minTimeBetweenBufferReevaluationMs,
        Clock clock) {
      this(
          /* bandwidthMeter= */ null,
          minDurationForQualityIncreaseMs,
          maxDurationForQualityDecreaseMs,
          minDurationToRetainAfterDiscardMs,
          bandwidthFraction,
          bufferedFractionToLiveEdgeForQualityIncrease,
          minTimeBetweenBufferReevaluationMs,
          clock);
    }

    /**
     * @deprecated Use {@link #Factory(int, int, int, float, float, long, Clock)} instead. Custom
     *     bandwidth meter should be directly passed to the player in {@link ExoPlayerFactory}.
     */
    @Deprecated
    public Factory(
        @Nullable BandwidthMeter bandwidthMeter,
        int minDurationForQualityIncreaseMs,
        int maxDurationForQualityDecreaseMs,
        int minDurationToRetainAfterDiscardMs,
        float bandwidthFraction,
        float bufferedFractionToLiveEdgeForQualityIncrease,
        long minTimeBetweenBufferReevaluationMs,
        Clock clock) {
      this.bandwidthMeter = bandwidthMeter;
      this.minDurationForQualityIncreaseMs = minDurationForQualityIncreaseMs;
      this.maxDurationForQualityDecreaseMs = maxDurationForQualityDecreaseMs;
      this.minDurationToRetainAfterDiscardMs = minDurationToRetainAfterDiscardMs;
      this.bandwidthFraction = bandwidthFraction;
      this.bufferedFractionToLiveEdgeForQualityIncrease =
          bufferedFractionToLiveEdgeForQualityIncrease;
      this.minTimeBetweenBufferReevaluationMs = minTimeBetweenBufferReevaluationMs;
      this.clock = clock;
      trackBitrateEstimator = TrackBitrateEstimator.DEFAULT;
    }

    /**
     * Sets a TrackBitrateEstimator.
     *
     * <p>This method is experimental, and will be renamed or removed in a future release.
     *
     * @param trackBitrateEstimator A {@link TrackBitrateEstimator}.
     */
    public void experimental_setTrackBitrateEstimator(TrackBitrateEstimator trackBitrateEstimator) {
      this.trackBitrateEstimator = trackBitrateEstimator;
    }

    /**
     * Enables blocking of the total fixed track selection bandwidth.
     *
     * <p>This method is experimental, and will be renamed or removed in a future release.
     */
    public void experimental_enableBlockFixedTrackSelectionBandwidth() {
      this.blockFixedTrackSelectionBandwidth = true;
    }

    @Override
    public @NullableType TrackSelection[] createTrackSelections(
        @NullableType Definition[] definitions, BandwidthMeter bandwidthMeter) {
      TrackSelection[] selections = new TrackSelection[definitions.length];
      AdaptiveTrackSelection adaptiveSelection = null;
      int totalFixedBandwidth = 0;
      for (int i = 0; i < definitions.length; i++) {
        Definition definition = definitions[i];
        if (definition == null) {
          continue;
        }
        if (definition.tracks.length > 1) {
          adaptiveSelection =
              createAdaptiveTrackSelection(definition.group, bandwidthMeter, definition.tracks);
          selections[i] = adaptiveSelection;
        } else {
          selections[i] = new FixedTrackSelection(definition.group, definition.tracks[0]);
          int trackBitrate = definition.group.getFormat(definition.tracks[0]).bitrate;
          if (trackBitrate != Format.NO_VALUE) {
            totalFixedBandwidth += trackBitrate;
          }
        }
      }
      if (blockFixedTrackSelectionBandwidth && adaptiveSelection != null) {
        adaptiveSelection.experimental_setNonAllocatableBandwidth(totalFixedBandwidth);
      }
      return selections;
    }

    private AdaptiveTrackSelection createAdaptiveTrackSelection(
        TrackGroup group, BandwidthMeter bandwidthMeter, int[] tracks) {
      if (this.bandwidthMeter != null) {
        bandwidthMeter = this.bandwidthMeter;
      }
      AdaptiveTrackSelection adaptiveTrackSelection =
          new AdaptiveTrackSelection(
              group,
              tracks,
              new DefaultBandwidthProvider(bandwidthMeter, bandwidthFraction),
              minDurationForQualityIncreaseMs,
              maxDurationForQualityDecreaseMs,
              minDurationToRetainAfterDiscardMs,
              bufferedFractionToLiveEdgeForQualityIncrease,
              minTimeBetweenBufferReevaluationMs,
              clock);
      adaptiveTrackSelection.experimental_setTrackBitrateEstimator(trackBitrateEstimator);
      return adaptiveTrackSelection;
    }
  }

  public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
  public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
  public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
  public static final float DEFAULT_BANDWIDTH_FRACTION = 0.75f;
  public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0.75f;
  public static final long DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 2000;

  private final BandwidthProvider bandwidthProvider;
  private final long minDurationForQualityIncreaseUs;
  private final long maxDurationForQualityDecreaseUs;
  private final long minDurationToRetainAfterDiscardUs;
  private final float bufferedFractionToLiveEdgeForQualityIncrease;
  private final long minTimeBetweenBufferReevaluationMs;
  private final Clock clock;
  private final Format[] formats;
  private final int[] formatBitrates;
  private final int[] trackBitrates;

  private TrackBitrateEstimator trackBitrateEstimator;
  private float playbackSpeed;
  private int selectedIndex;
  private int reason;
  private long lastBufferEvaluationMs;

  /**
   * @param group The {@link TrackGroup}.
   * @param tracks The indices of the selected tracks within the {@link TrackGroup}. Must not be
   *     empty. May be in any order.
   * @param bandwidthMeter Provides an estimate of the currently available bandwidth.
   */
  public AdaptiveTrackSelection(TrackGroup group, int[] tracks,
      BandwidthMeter bandwidthMeter) {
    this(
        group,
        tracks,
        bandwidthMeter,
        DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS,
        DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS,
        DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS,
        DEFAULT_BANDWIDTH_FRACTION,
        DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE,
        DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS,
        Clock.DEFAULT);
  }

  /**
   * @param group The {@link TrackGroup}.
   * @param tracks The indices of the selected tracks within the {@link TrackGroup}. Must not be
   *     empty. May be in any order.
   * @param bandwidthMeter Provides an estimate of the currently available bandwidth.
   * @param minDurationForQualityIncreaseMs The minimum duration of buffered data required for the
   *     selected track to switch to one of higher quality.
   * @param maxDurationForQualityDecreaseMs The maximum duration of buffered data required for the
   *     selected track to switch to one of lower quality.
   * @param minDurationToRetainAfterDiscardMs When switching to a track of significantly higher
   *     quality, the selection may indicate that media already buffered at the lower quality can be
   *     discarded to speed up the switch. This is the minimum duration of media that must be
   *     retained at the lower quality.
   * @param bandwidthFraction The fraction of the available bandwidth that the selection should
   *     consider available for use. Setting to a value less than 1 is recommended to account for
   *     inaccuracies in the bandwidth estimator.
   * @param bufferedFractionToLiveEdgeForQualityIncrease For live streaming, the fraction of the
   *     duration from current playback position to the live edge that has to be buffered before the
   *     selected track can be switched to one of higher quality. This parameter is only applied
   *     when the playback position is closer to the live edge than {@code
   *     minDurationForQualityIncreaseMs}, which would otherwise prevent switching to a higher
   *     quality from happening.
   * @param minTimeBetweenBufferReevaluationMs The track selection may periodically reevaluate its
   *     buffer and discard some chunks of lower quality to improve the playback quality if network
   *     condition has changed. This is the minimum duration between 2 consecutive buffer
   *     reevaluation calls.
   */
  public AdaptiveTrackSelection(
      TrackGroup group,
      int[] tracks,
      BandwidthMeter bandwidthMeter,
      long minDurationForQualityIncreaseMs,
      long maxDurationForQualityDecreaseMs,
      long minDurationToRetainAfterDiscardMs,
      float bandwidthFraction,
      float bufferedFractionToLiveEdgeForQualityIncrease,
      long minTimeBetweenBufferReevaluationMs,
      Clock clock) {
    this(
        group,
        tracks,
        new DefaultBandwidthProvider(bandwidthMeter, bandwidthFraction),
        minDurationForQualityIncreaseMs,
        maxDurationForQualityDecreaseMs,
        minDurationToRetainAfterDiscardMs,
        bufferedFractionToLiveEdgeForQualityIncrease,
        minTimeBetweenBufferReevaluationMs,
        clock);
  }

  private AdaptiveTrackSelection(
      TrackGroup group,
      int[] tracks,
      BandwidthProvider bandwidthProvider,
      long minDurationForQualityIncreaseMs,
      long maxDurationForQualityDecreaseMs,
      long minDurationToRetainAfterDiscardMs,
      float bufferedFractionToLiveEdgeForQualityIncrease,
      long minTimeBetweenBufferReevaluationMs,
      Clock clock) {
    super(group, tracks);
    this.bandwidthProvider = bandwidthProvider;
    this.minDurationForQualityIncreaseUs = minDurationForQualityIncreaseMs * 1000L;
    this.maxDurationForQualityDecreaseUs = maxDurationForQualityDecreaseMs * 1000L;
    this.minDurationToRetainAfterDiscardUs = minDurationToRetainAfterDiscardMs * 1000L;
    this.bufferedFractionToLiveEdgeForQualityIncrease =
        bufferedFractionToLiveEdgeForQualityIncrease;
    this.minTimeBetweenBufferReevaluationMs = minTimeBetweenBufferReevaluationMs;
    this.clock = clock;
    playbackSpeed = 1f;
    reason = C.SELECTION_REASON_UNKNOWN;
    lastBufferEvaluationMs = C.TIME_UNSET;
    trackBitrateEstimator = TrackBitrateEstimator.DEFAULT;
    formats = new Format[length];
    formatBitrates = new int[length];
    trackBitrates = new int[length];
    for (int i = 0; i < length; i++) {
      @SuppressWarnings("nullness:method.invocation.invalid")
      Format format = getFormat(i);
      formats[i] = format;
      formatBitrates[i] = formats[i].bitrate;
    }
  }

  /**
   * Sets a TrackBitrateEstimator.
   *
   * <p>This method is experimental, and will be renamed or removed in a future release.
   *
   * @param trackBitrateEstimator A {@link TrackBitrateEstimator}.
   */
  public void experimental_setTrackBitrateEstimator(TrackBitrateEstimator trackBitrateEstimator) {
    this.trackBitrateEstimator = trackBitrateEstimator;
  }

  /**
   * Sets the non-allocatable bandwidth, which shouldn't be considered available.
   *
   * <p>This method is experimental, and will be renamed or removed in a future release.
   *
   * @param nonAllocatableBandwidth The non-allocatable bandwidth in bits per second.
   */
  public void experimental_setNonAllocatableBandwidth(long nonAllocatableBandwidth) {
    ((DefaultBandwidthProvider) bandwidthProvider)
        .experimental_setNonAllocatableBandwidth(nonAllocatableBandwidth);
  }

  @Override
  public void enable() {
    lastBufferEvaluationMs = C.TIME_UNSET;
  }

  @Override
  public void onPlaybackSpeed(float playbackSpeed) {
    this.playbackSpeed = playbackSpeed;
  }

  @Override
  public void updateSelectedTrack(
      long playbackPositionUs,
      long bufferedDurationUs,
      long availableDurationUs,
      List<? extends MediaChunk> queue,
      MediaChunkIterator[] mediaChunkIterators) {
    long nowMs = clock.elapsedRealtime();

    // Update the estimated track bitrates.
    trackBitrateEstimator.getBitrates(formats, queue, mediaChunkIterators, trackBitrates);

    // Make initial selection
    if (reason == C.SELECTION_REASON_UNKNOWN) {
      reason = C.SELECTION_REASON_INITIAL;
      selectedIndex = determineIdealSelectedIndex(nowMs, trackBitrates);
      return;
    }

    // Stash the current selection, then make a new one.
    int currentSelectedIndex = selectedIndex;
    selectedIndex = determineIdealSelectedIndex(nowMs, trackBitrates);
    if (selectedIndex == currentSelectedIndex) {
      return;
    }

    if (!isBlacklisted(currentSelectedIndex, nowMs)) {
      // Revert back to the current selection if conditions are not suitable for switching.
      Format currentFormat = getFormat(currentSelectedIndex);
      Format selectedFormat = getFormat(selectedIndex);
      if (selectedFormat.bitrate > currentFormat.bitrate
          && bufferedDurationUs < minDurationForQualityIncreaseUs(availableDurationUs)) {
        // The selected track is a higher quality, but we have insufficient buffer to safely switch
        // up. Defer switching up for now.
        selectedIndex = currentSelectedIndex;
      } else if (selectedFormat.bitrate < currentFormat.bitrate
          && bufferedDurationUs >= maxDurationForQualityDecreaseUs) {
        // The selected track is a lower quality, but we have sufficient buffer to defer switching
        // down for now.
        selectedIndex = currentSelectedIndex;
      }
    }
    // If we adapted, update the trigger.
    if (selectedIndex != currentSelectedIndex) {
      reason = C.SELECTION_REASON_ADAPTIVE;
    }
  }

  @Override
  public int getSelectedIndex() {
    return selectedIndex;
  }

  @Override
  public int getSelectionReason() {
    return reason;
  }

  @Override
  public @Nullable Object getSelectionData() {
    return null;
  }

  @Override
  public int evaluateQueueSize(long playbackPositionUs, List<? extends MediaChunk> queue) {
    long nowMs = clock.elapsedRealtime();
    if (!shouldEvaluateQueueSize(nowMs)) {
      return queue.size();
    }

    lastBufferEvaluationMs = nowMs;
    if (queue.isEmpty()) {
      return 0;
    }

    int queueSize = queue.size();
    MediaChunk lastChunk = queue.get(queueSize - 1);
    long playoutBufferedDurationBeforeLastChunkUs =
        Util.getPlayoutDurationForMediaDuration(
            lastChunk.startTimeUs - playbackPositionUs, playbackSpeed);
    long minDurationToRetainAfterDiscardUs = getMinDurationToRetainAfterDiscardUs();
    if (playoutBufferedDurationBeforeLastChunkUs < minDurationToRetainAfterDiscardUs) {
      return queueSize;
    }
    int idealSelectedIndex = determineIdealSelectedIndex(nowMs, formatBitrates);
    Format idealFormat = getFormat(idealSelectedIndex);
    // If the chunks contain video, discard from the first SD chunk beyond
    // minDurationToRetainAfterDiscardUs whose resolution and bitrate are both lower than the ideal
    // track.
    for (int i = 0; i < queueSize; i++) {
      MediaChunk chunk = queue.get(i);
      Format format = chunk.trackFormat;
      long mediaDurationBeforeThisChunkUs = chunk.startTimeUs - playbackPositionUs;
      long playoutDurationBeforeThisChunkUs =
          Util.getPlayoutDurationForMediaDuration(mediaDurationBeforeThisChunkUs, playbackSpeed);
      if (playoutDurationBeforeThisChunkUs >= minDurationToRetainAfterDiscardUs
          && format.bitrate < idealFormat.bitrate
          && format.height != Format.NO_VALUE && format.height < 720
          && format.width != Format.NO_VALUE && format.width < 1280
          && format.height < idealFormat.height) {
        return i;
      }
    }
    return queueSize;
  }

  /**
   * Called when updating the selected track to determine whether a candidate track can be selected.
   *
   * @param format The {@link Format} of the candidate track.
   * @param trackBitrate The estimated bitrate of the track. May differ from {@link Format#bitrate}
   *     if a more accurate estimate of the current track bitrate is available.
   * @param playbackSpeed The current playback speed.
   * @param effectiveBitrate The bitrate available to this selection.
   * @return Whether this {@link Format} can be selected.
   */
  @SuppressWarnings("unused")
  protected boolean canSelectFormat(
      Format format, int trackBitrate, float playbackSpeed, long effectiveBitrate) {
    return Math.round(trackBitrate * playbackSpeed) <= effectiveBitrate;
  }

  /**
   * Called from {@link #evaluateQueueSize(long, List)} to determine whether an evaluation should be
   * performed.
   *
   * @param nowMs The current value of {@link Clock#elapsedRealtime()}.
   * @return Whether an evaluation should be performed.
   */
  protected boolean shouldEvaluateQueueSize(long nowMs) {
    return lastBufferEvaluationMs == C.TIME_UNSET
        || nowMs - lastBufferEvaluationMs >= minTimeBetweenBufferReevaluationMs;
  }

  /**
   * Called from {@link #evaluateQueueSize(long, List)} to determine the minimum duration of buffer
   * to retain after discarding chunks.
   *
   * @return The minimum duration of buffer to retain after discarding chunks, in microseconds.
   */
  protected long getMinDurationToRetainAfterDiscardUs() {
    return minDurationToRetainAfterDiscardUs;
  }

  /**
   * Computes the ideal selected index ignoring buffer health.
   *
   * @param nowMs The current time in the timebase of {@link Clock#elapsedRealtime()}, or {@link
   *     Long#MIN_VALUE} to ignore blacklisting.
   * @param trackBitrates The estimated track bitrates. May differ from format bitrates if more
   *     accurate estimates of the current track bitrates are available.
   */
  private int determineIdealSelectedIndex(long nowMs, int[] trackBitrates) {
    long effectiveBitrate = bandwidthProvider.getAllocatedBandwidth();
    int lowestBitrateNonBlacklistedIndex = 0;
    for (int i = 0; i < length; i++) {
      if (nowMs == Long.MIN_VALUE || !isBlacklisted(i, nowMs)) {
        Format format = getFormat(i);
        if (canSelectFormat(format, trackBitrates[i], playbackSpeed, effectiveBitrate)) {
          return i;
        } else {
          lowestBitrateNonBlacklistedIndex = i;
        }
      }
    }
    return lowestBitrateNonBlacklistedIndex;
  }

  private long minDurationForQualityIncreaseUs(long availableDurationUs) {
    boolean isAvailableDurationTooShort = availableDurationUs != C.TIME_UNSET
        && availableDurationUs <= minDurationForQualityIncreaseUs;
    return isAvailableDurationTooShort
        ? (long) (availableDurationUs * bufferedFractionToLiveEdgeForQualityIncrease)
        : minDurationForQualityIncreaseUs;
  }

  /** Provides the allocated bandwidth. */
  private interface BandwidthProvider {

    /** Returns the allocated bitrate. */
    long getAllocatedBandwidth();
  }

  private static final class DefaultBandwidthProvider implements BandwidthProvider {

    private final BandwidthMeter bandwidthMeter;
    private final float bandwidthFraction;

    private long nonAllocatableBandwidth;

    /* package */ DefaultBandwidthProvider(BandwidthMeter bandwidthMeter, float bandwidthFraction) {
      this.bandwidthMeter = bandwidthMeter;
      this.bandwidthFraction = bandwidthFraction;
    }

    @Override
    public long getAllocatedBandwidth() {
      long totalBandwidth = (long) (bandwidthMeter.getBitrateEstimate() * bandwidthFraction);
      return Math.max(0L, totalBandwidth - nonAllocatableBandwidth);
    }

    /* package */ void experimental_setNonAllocatableBandwidth(long nonAllocatableBandwidth) {
      this.nonAllocatableBandwidth = nonAllocatableBandwidth;
    }
  }
}
