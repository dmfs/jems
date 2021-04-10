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
 * An abstract {@link Comparator} which delegates to another Comparator.
 */
public abstract class DelegatingComparator<T> implements Comparator<T>
{
    private final Comparator<T> mDelegate;


    protected DelegatingComparator(Comparator<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final int compare(T t, T t1)
    {
        return mDelegate.compare(t, t1);
    }
}
