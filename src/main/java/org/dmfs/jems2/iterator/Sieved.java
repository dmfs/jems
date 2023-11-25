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

import org.dmfs.jems2.Predicate;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * {@link Iterator} decorator which iterates all elements of the delegate which satisfy a given {@link Predicate}.
 */
public final class Sieved<E> extends BaseIterator<E>
{
    private final Iterator<E> mDelagate;
    private final Predicate<? super E> mPredicate;

    private E mNext;
    private boolean mHasNext;


    /**
     * Creates a sieved {@link Iterator} which iterates the elements of the given {@link Iterator} which match the given {@link Predicate}.
     *
     * @param predicate
     *     The {@link Predicate}.
     * @param delegate
     *     The {@link Iterator} to be sieved.
     */
    public Sieved(final Predicate<? super E> predicate, final Iterator<E> delegate)
    {
        mDelagate = delegate;
        mPredicate = predicate;
    }


    @Override
    public final boolean hasNext()
    {
        return moveToNext();
    }


    @Override
    public final E next()
    {
        if (!moveToNext())
        {
            throw new NoSuchElementException("No more elements to iterate");
        }

        mHasNext = false;
        return mNext;
    }


    /**
     * Ensures mNext has the value of the next element to return.
     */
    private boolean moveToNext()
    {
        if (mHasNext)
        {
            // still pointing to the next element
            return true;
        }
        while (mDelagate.hasNext())
        {
            E next = mDelagate.next();
            if (mPredicate.satisfiedBy(next))
            {
                mNext = next;
                mHasNext = true;
                return true;
            }
        }
        return false;
    }
}
