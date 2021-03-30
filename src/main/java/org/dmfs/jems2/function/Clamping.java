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

package org.dmfs.jems2.function;

import org.dmfs.jems2.Function;


/**
 * {@link Function} decorator that 'clamps' the value between the provided min and max values.
 */
public final class Clamping<T extends Comparable<T>> implements Function<T, T>
{
    private final T mMinValue;
    private final T mMaxValue;


    public Clamping(T minValue, T maxValue)
    {
        mMinValue = minValue;
        mMaxValue = maxValue;
    }


    @Override
    public T value(T argument)
    {
        if (argument.compareTo(mMinValue) < 0)
        {
            return mMinValue;
        }
        if (argument.compareTo(mMaxValue) > 0)
        {
            return mMaxValue;
        }
        return argument;
    }
}
