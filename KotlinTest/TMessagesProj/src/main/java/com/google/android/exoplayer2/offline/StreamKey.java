/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.offline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A key for a subset of media which can be separately loaded (a "stream").
 *
 * <p>The stream key consists of a period index, a group index within the period and a track index
 * within the group. The interpretation of these indices depends on the type of media for which the
 * stream key is used.
 */
public final class StreamKey implements Comparable<StreamKey> {

  /** The period index. */
  public final int periodIndex;
  /** The group index. */
  public final int groupIndex;
  /** The track index. */
  public final int trackIndex;

  /**
   * @param groupIndex The group index.
   * @param trackIndex The track index.
   */
  public StreamKey(int groupIndex, int trackIndex) {
    this(0, groupIndex, trackIndex);
  }

  /**
   * @param periodIndex The period index.
   * @param groupIndex The group index.
   * @param trackIndex The track index.
   */
  public StreamKey(int periodIndex, int groupIndex, int trackIndex) {
    this.periodIndex = periodIndex;
    this.groupIndex = groupIndex;
    this.trackIndex = trackIndex;
  }

  @Override
  public String toString() {
    return periodIndex + "." + groupIndex + "." + trackIndex;
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StreamKey that = (StreamKey) o;
    return periodIndex == that.periodIndex
        && groupIndex == that.groupIndex
        && trackIndex == that.trackIndex;
  }

  @Override
  public int hashCode() {
    int result = periodIndex;
    result = 31 * result + groupIndex;
    result = 31 * result + trackIndex;
    return result;
  }

  // Comparable implementation.

  @Override
  public int compareTo(@NonNull StreamKey o) {
    int result = periodIndex - o.periodIndex;
    if (result == 0) {
      result = groupIndex - o.groupIndex;
      if (result == 0) {
        result = trackIndex - o.trackIndex;
      }
    }
    return result;
  }
}
