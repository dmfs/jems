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

package org.dmfs.optional;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * The first value of an {@link Iterable}.
 *
 * @author Marten Gajda
 */
public final class First<T> implements Optional<T>
{
    private final Iterable<T> mIterable;
    private Optional<T> mDelegate;


    /**
     * Creates the {@link Optional} first value of the given {@link Iterable}.
     *
     * @param iterable
     *         A {@link Iterable}.
     */
    public First(Iterable<T> iterable)
    {
        mIterable = iterable;
    }


    @Override
    public boolean isPresent()
    {
        return delegate().isPresent();
    }


    @Override
    public T value(T defaultValue)
    {
        return delegate().value(defaultValue);
    }


    @Override
    public T value() throws NoSuchElementException
    {
        return delegate().value();
    }


    private Optional<T> delegate()
    {
        if (mDelegate == null)
        {
            Iterator<T> iterator = mIterable.iterator();
            mDelegate = iterator.hasNext() ? new Present<>(iterator.next()) : Absent.<T>absent();
        }
        return mDelegate;
    }
}
