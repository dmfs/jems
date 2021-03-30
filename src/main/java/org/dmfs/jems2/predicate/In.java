/*
 * Copyright 2021 dmfs GmbH
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

package org.dmfs.jems2.predicate;

import org.dmfs.jems2.Predicate;


/**
 * A {@link Predicate} which is satisfied by {@link Comparable} elements within a specific closed interval.
 */
public final class In<T extends Comparable<T>> implements Predicate<T>
{
    private final T mLowerBoundary;
    private final T mUpperBoundary;


    public In(T lowerBoundary, T upperBoundary)
    {
        mLowerBoundary = lowerBoundary;
        mUpperBoundary = upperBoundary;
    }


    @Override
    public boolean satisfiedBy(T element)
    {
        return mLowerBoundary.compareTo(element) <= 0 && mUpperBoundary.compareTo(element) >= 0;
    }
}
