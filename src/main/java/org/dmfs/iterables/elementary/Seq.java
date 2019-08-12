/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.iterables.elementary;

import java.util.Iterator;


/**
 * {@link Iterable} sequence of values.
 *
 * @param <T>
 *         The type of the iterated elements.
 *
 * @author Marten Gajda
 * @deprecated in favour of {@link org.dmfs.jems.iterable.elementary.Seq}
 */
@Deprecated
public final class Seq<T> implements Iterable<T>
{
    private final T[] mArray;


    @SafeVarargs
    public Seq(T... elements)
    {
        mArray = elements;
    }


    @Override
    public Iterator<T> iterator()
    {
        return new org.dmfs.iterators.elementary.Seq<>(mArray);
    }
}
