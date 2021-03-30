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

package org.dmfs.jems2.comparator;

import java.util.Comparator;


/**
 * A {@link Comparator} which reverses the order of another {@link Comparator}.
 */
public final class Reverse<T> implements Comparator<T>
{
    private final Comparator<? super T> mDelegate;


    public Reverse(Comparator<? super T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public int compare(T left, T right)
    {
        return -1 * mDelegate.compare(left, right);
    }
}
