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

package org.dmfs.jems2.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.dmfs.jems2.iterator.EmptyIterator.emptyIterator;


/**
 * An {@link Iterator} that concatenates the results of other {@link Iterator}s. This means, it iterates the elements of each {@link Iterator} before moving
 * on to the next {@link Iterator}.
 */
public final class Concat<E> extends BaseIterator<E>
{
    private final Iterator<Iterator<E>> mIterators;

    private Iterator<E> mCurrentIterator;


    /**
     * Constructor of an {@link Iterator} that serializes the elements of other {@link Iterator}s.
     *
     * @param iterators
     *     An array of {@link Iterator}s.
     */
    @SafeVarargs
    public Concat(final Iterator<E>... iterators)
    {
        this(new Seq<>(iterators));
    }


    /**
     * Constructor of an {@link Iterator} that serializes the elements of the {@link Iterator}s iterated by the given {@link Iterator}.
     *
     * @param iteratorIterator
     *     An {@link Iterator} that iterates other iterators of type &lt;E&gt;.
     */
    public Concat(final Iterator<Iterator<E>> iteratorIterator)
    {
        mIterators = iteratorIterator;
        mCurrentIterator = emptyIterator();
    }


    @Override
    public boolean hasNext()
    {
        while (!mCurrentIterator.hasNext() && mIterators.hasNext())
        {
            // advance to the next iterator that contains elements to iterate
            mCurrentIterator = mIterators.next();
        }

        return mCurrentIterator.hasNext();
    }


    @Override
    public E next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException("No more elements to iterate.");
        }

        return mCurrentIterator.next();
    }
}