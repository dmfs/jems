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

package org.dmfs.jems2.iterable;

import org.dmfs.jems2.single.Collected;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


/**
 * An {@link Iterable} decorator which returns the elements of the delegate in a sorted order, determined by a given {@link Comparator}.
 */
public final class Sorted<T> implements Iterable<T>
{
    private final Iterable<T> mDelegate;
    private final Comparator<? super T> mComparator;


    public Sorted(Comparator<? super T> comparator, Iterable<T> delegate)
    {
        mDelegate = delegate;
        mComparator = comparator;
    }


    @Override
    public Iterator<T> iterator()
    {
        List<T> result = new Collected<>(ArrayList::new, mDelegate).value();
        result.sort(mComparator);
        return result.iterator();
    }
}
