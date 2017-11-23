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

package org.dmfs.jems.iterable.composite;

import org.dmfs.iterables.decorators.Mapped;
import org.dmfs.jems.function.Function;

import java.util.Iterator;


/**
 * An {@link Iterable} which joins other {@link Iterable}s by iterating the values of them one after another.
 *
 * @param <T>
 *         The type of the iterated elements.
 *
 * @author Marten Gajda
 */
public final class Joined<T> implements Iterable<T>
{
    private final Iterable<Iterable<T>> mIterables;


    public <V> Joined(final Function<V, Iterable<T>> function, Iterable<V> iterables)
    {
        this(new Mapped<>(iterables, new org.dmfs.iterators.Function<V, Iterable<T>>()
        {

            @Override
            public Iterable<T> apply(V argument)
            {
                return function.value(argument);
            }
        }));
    }


    public Joined(Iterable<Iterable<T>> iterables)
    {
        mIterables = iterables;
    }


    @Override
    public Iterator<T> iterator()
    {
        return new org.dmfs.iterators.decorators.Flattened<>(mIterables.iterator());
    }
}
