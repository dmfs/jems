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

package org.dmfs.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An iterator to iterate a single value.
 *
 * @param <E>
 *         The type of the iterated value.
 *
 * @author Marten Gajda
 */
public final class SingletonIterator<E> extends AbstractBaseIterator<E>
{
    private boolean mHasNext = true;
    private E mValue;


    /**
     * Creates an {@link Iterator} that iterates (only) the given value.
     *
     * @param value
     *         The sole value to iterate.
     */
    public SingletonIterator(final E value)
    {
        mValue = value;
    }


    @Override
    public boolean hasNext()
    {
        return mHasNext;
    }


    @Override
    public E next()
    {
        if (!mHasNext)
        {
            throw new NoSuchElementException("No more elements to iterate.");
        }
        mHasNext = false;
        return mValue;
    }
}