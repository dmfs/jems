/*
 * Copyright 2019 dmfs GmbH
 *
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

package org.dmfs.jems.set.elementary;

import org.dmfs.jems.set.Set;


/**
 * Represents a <i>closed</i> interval of {@link Comparable} objects.
 *
 * @author Marten Gajda
 */
@Deprecated
public final class Interval<T extends Comparable<T>> implements Set<T>
{
    private final T mStart;
    private final T mEnd;


    /**
     * Creates an {@link Interval}. Note, if {@code start > end} this will degenerate to an empty interval.
     *
     * @param start
     *         The lower bound of the interval.
     * @param end
     *         The upper bound of the interval.
     */
    public Interval(T start, T end)
    {
        mStart = start;
        mEnd = end;
    }


    @Override
    public boolean contains(T element)
    {
        return mStart.compareTo(element) <= 0 && mEnd.compareTo(element) >= 0;
    }
}
