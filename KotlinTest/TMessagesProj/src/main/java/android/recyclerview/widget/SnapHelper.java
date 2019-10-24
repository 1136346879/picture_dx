/*
 * Copyright 2018 The Android Open Source Project
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

package android.recyclerview.widget;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Class intended to support snapping for a {@link RecyclerTmView}.
 * <p>
 * SnapHelper tries to handle fling as well but for this to work properly, the
 * {@link RecyclerTmView.LayoutManager} must implement the {@link RecyclerTmView.SmoothScroller.ScrollVectorProvider} interface or
 * you should override {@link #onFling(int, int)} and handle fling manually.
 */
public abstract class SnapHelper extends RecyclerTmView.OnFlingListener {

    static final float MILLISECONDS_PER_INCH = 100f;

    RecyclerTmView mRecyclerTmView;
    private Scroller mGravityScroller;

    // Handles the snap on scroll case.
    private final RecyclerTmView.OnScrollListener mScrollListener =
            new RecyclerTmView.OnScrollListener() {
                boolean mScrolled = false;

                @Override
                public void onScrollStateChanged(RecyclerTmView recyclerTmView, int newState) {
                    super.onScrollStateChanged(recyclerTmView, newState);
                    if (newState == RecyclerTmView.SCROLL_STATE_IDLE && mScrolled) {
                        mScrolled = false;
                        snapToTargetExistingView();
                    }
                }

                @Override
                public void onScrolled(RecyclerTmView recyclerTmView, int dx, int dy) {
                    if (dx != 0 || dy != 0) {
                        mScrolled = true;
                    }
                }
            };

    @Override
    public boolean onFling(int velocityX, int velocityY) {
        RecyclerTmView.LayoutManager layoutManager = mRecyclerTmView.getLayoutManager();
        if (layoutManager == null) {
            return false;
        }
        RecyclerTmView.Adapter adapter = mRecyclerTmView.getAdapter();
        if (adapter == null) {
            return false;
        }
        int minFlingVelocity = mRecyclerTmView.getMinFlingVelocity();
        return (Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity)
                && snapFromFling(layoutManager, velocityX, velocityY);
    }

    /**
     * Attaches the {@link SnapHelper} to the provided RecyclerView, by calling
     * {@link RecyclerTmView#setOnFlingListener(RecyclerTmView.OnFlingListener)}.
     * You can call this method with {@code null} to detach it from the current RecyclerView.
     *
     * @param recyclerTmView The RecyclerView instance to which you want to add this helper or
     *                     {@code null} if you want to remove SnapHelper from the current
     *                     RecyclerView.
     *
     * @throws IllegalArgumentException if there is already a {@link RecyclerTmView.OnFlingListener}
     * attached to the provided {@link RecyclerTmView}.
     *
     */
    public void attachToRecyclerView(@Nullable RecyclerTmView recyclerTmView)
            throws IllegalStateException {
        if (mRecyclerTmView == recyclerTmView) {
            return; // nothing to do
        }
        if (mRecyclerTmView != null) {
            destroyCallbacks();
        }
        mRecyclerTmView = recyclerTmView;
        if (mRecyclerTmView != null) {
            setupCallbacks();
            mGravityScroller = new Scroller(mRecyclerTmView.getContext(),
                    new DecelerateInterpolator());
            snapToTargetExistingView();
        }
    }

    /**
     * Called when an instance of a {@link RecyclerTmView} is attached.
     */
    private void setupCallbacks() throws IllegalStateException {
        if (mRecyclerTmView.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        mRecyclerTmView.addOnScrollListener(mScrollListener);
        mRecyclerTmView.setOnFlingListener(this);
    }

    /**
     * Called when the instance of a {@link RecyclerTmView} is detached.
     */
    private void destroyCallbacks() {
        mRecyclerTmView.removeOnScrollListener(mScrollListener);
        mRecyclerTmView.setOnFlingListener(null);
    }

    /**
     * Calculated the estimated scroll distance in each direction given velocities on both axes.
     *
     * @param velocityX     Fling velocity on the horizontal axis.
     * @param velocityY     Fling velocity on the vertical axis.
     *
     * @return array holding the calculated distances in x and y directions
     * respectively.
     */
    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        int[] outDist = new int[2];
        mGravityScroller.fling(0, 0, velocityX, velocityY,
                Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        outDist[0] = mGravityScroller.getFinalX();
        outDist[1] = mGravityScroller.getFinalY();
        return outDist;
    }

    /**
     * Helper method to facilitate for snapping triggered by a fling.
     *
     * @param layoutManager The {@link RecyclerTmView.LayoutManager} associated with the attached
     *                      {@link RecyclerTmView}.
     * @param velocityX     Fling velocity on the horizontal axis.
     * @param velocityY     Fling velocity on the vertical axis.
     *
     * @return true if it is handled, false otherwise.
     */
    private boolean snapFromFling(@NonNull RecyclerTmView.LayoutManager layoutManager, int velocityX,
                                  int velocityY) {
        if (!(layoutManager instanceof RecyclerTmView.SmoothScroller.ScrollVectorProvider)) {
            return false;
        }

        RecyclerTmView.SmoothScroller smoothScroller = createScroller(layoutManager);
        if (smoothScroller == null) {
            return false;
        }

        int targetPosition = findTargetSnapPosition(layoutManager, velocityX, velocityY);
        if (targetPosition == RecyclerTmView.NO_POSITION) {
            return false;
        }

        smoothScroller.setTargetPosition(targetPosition);
        layoutManager.startSmoothScroll(smoothScroller);
        return true;
    }

    /**
     * Snaps to a target view which currently exists in the attached {@link RecyclerTmView}. This
     * method is used to snap the view when the {@link RecyclerTmView} is first attached; when
     * snapping was triggered by a scroll and when the fling is at its final stages.
     */
    void snapToTargetExistingView() {
        if (mRecyclerTmView == null) {
            return;
        }
        RecyclerTmView.LayoutManager layoutManager = mRecyclerTmView.getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        View snapView = findSnapView(layoutManager);
        if (snapView == null) {
            return;
        }
        int[] snapDistance = calculateDistanceToFinalSnap(layoutManager, snapView);
        if (snapDistance[0] != 0 || snapDistance[1] != 0) {
            mRecyclerTmView.smoothScrollBy(snapDistance[0], snapDistance[1]);
        }
    }

    /**
     * Creates a scroller to be used in the snapping implementation.
     *
     * @param layoutManager     The {@link RecyclerTmView.LayoutManager} associated with the attached
     *                          {@link RecyclerTmView}.
     *
     * @return a {@link RecyclerTmView.SmoothScroller} which will handle the scrolling.
     */
    @Nullable
    protected RecyclerTmView.SmoothScroller createScroller(RecyclerTmView.LayoutManager layoutManager) {
        return createSnapScroller(layoutManager);
    }

    /**
     * Creates a scroller to be used in the snapping implementation.
     *
     * @param layoutManager     The {@link RecyclerTmView.LayoutManager} associated with the attached
     *                          {@link RecyclerTmView}.
     *
     * @return a {@link LinearSmoothScroller} which will handle the scrolling.
     * @deprecated use {@link #createScroller(RecyclerTmView.LayoutManager)} instead.
     */
    @Nullable
    @Deprecated
    protected LinearSmoothScroller createSnapScroller(RecyclerTmView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerTmView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new LinearSmoothScroller(mRecyclerTmView.getContext()) {
            @Override
            protected void onTargetFound(View targetView, RecyclerTmView.State state, Action action) {
                if (mRecyclerTmView == null) {
                    // The associated RecyclerView has been removed so there is no action to take.
                    return;
                }
                int[] snapDistances = calculateDistanceToFinalSnap(mRecyclerTmView.getLayoutManager(),
                        targetView);
                final int dx = snapDistances[0];
                final int dy = snapDistances[1];
                final int time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
                if (time > 0) {
                    action.update(dx, dy, time, mDecelerateInterpolator);
                }
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };
    }

    /**
     * Override this method to snap to a particular point within the target view or the container
     * view on any axis.
     * <p>
     * This method is called when the {@link SnapHelper} has intercepted a fling and it needs
     * to know the exact distance required to scroll by in order to snap to the target view.
     *
     * @param layoutManager the {@link RecyclerTmView.LayoutManager} associated with the attached
     *                      {@link RecyclerTmView}
     * @param targetView the target view that is chosen as the view to snap
     *
     * @return the output coordinates the put the result into. out[0] is the distance
     * on horizontal axis and out[1] is the distance on vertical axis.
     */
    @SuppressWarnings("WeakerAccess")
    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull RecyclerTmView.LayoutManager layoutManager,
            @NonNull View targetView);

    /**
     * Override this method to provide a particular target view for snapping.
     * <p>
     * This method is called when the {@link SnapHelper} is ready to start snapping and requires
     * a target view to snap to. It will be explicitly called when the scroll state becomes idle
     * after a scroll. It will also be called when the {@link SnapHelper} is preparing to snap
     * after a fling and requires a reference view from the current set of child views.
     * <p>
     * If this method returns {@code null}, SnapHelper will not snap to any view.
     *
     * @param layoutManager the {@link RecyclerTmView.LayoutManager} associated with the attached
     *                      {@link RecyclerTmView}
     *
     * @return the target view to which to snap on fling or end of scroll
     */
    @SuppressWarnings("WeakerAccess")
    @Nullable
    public abstract View findSnapView(RecyclerTmView.LayoutManager layoutManager);

    /**
     * Override to provide a particular adapter target position for snapping.
     *
     * @param layoutManager the {@link RecyclerTmView.LayoutManager} associated with the attached
     *                      {@link RecyclerTmView}
     * @param velocityX fling velocity on the horizontal axis
     * @param velocityY fling velocity on the vertical axis
     *
     * @return the target adapter position to you want to snap or {@link RecyclerTmView#NO_POSITION}
     *         if no snapping should happen
     */
    public abstract int findTargetSnapPosition(RecyclerTmView.LayoutManager layoutManager, int velocityX,
                                               int velocityY);
}
