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

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Pair;
import org.dmfs.jems2.optional.Next;
import org.dmfs.jems2.pair.LeftSidedPair;
import org.dmfs.jems2.pair.RightSidedPair;
import org.dmfs.jems2.pair.ValuePair;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An {@link Iterator} returning the differences of two given (sorted) {@link Iterator}s. The input {@link Iterator}s have to be sorted by the comparison
 * criterion, otherwise the result is undefined.
 */
public final class Diff<Left, Right> extends BaseIterator<Pair<Optional<Left>, Optional<Right>>>
{
    private final Iterator<Left> mLefts;
    private final Iterator<Right> mRights;
    private final BiFunction<? super Left, ? super Right, Integer> mComparatorFunction;

    private Optional<Left> mNextLeft;
    private Optional<Right> mNextRight;


    public Diff(Iterator<Left> leftIterator,
        Iterator<Right> rightIterator,
        BiFunction<? super Left, ? super Right, Integer> comparatorFunction)
    {
        mLefts = leftIterator;
        mRights = rightIterator;
        mComparatorFunction = comparatorFunction;
        mNextLeft = new Next<>(leftIterator);
        mNextRight = new Next<>(rightIterator);
    }


    @Override
    public boolean hasNext()
    {
        return mNextLeft.isPresent() || mNextRight.isPresent();
    }


    @Override
    public Pair<Optional<Left>, Optional<Right>> next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException("No more elements to iterate");
        }
        // TODO: can we make this any more "declarative"?
        if (!mNextLeft.isPresent())
        {
            // no further elements on the left, advance the right side
            Optional<Right> right = mNextRight;
            mNextRight = new Next<>(mRights);
            return new RightSidedPair<>(right);
        }
        if (!mNextRight.isPresent())
        {
            // no further elements on the right, advance the left side
            Optional<Left> left = mNextLeft;
            mNextLeft = new Next<>(mLefts);
            return new LeftSidedPair<>(left);
        }

        int result = mComparatorFunction.value(mNextLeft.value(), mNextRight.value());
        if (result < 0)
        {
            // return the smaller result, the left one in this case
            Optional<Left> nextLeft = mNextLeft;
            // advance left
            mNextLeft = new Next<>(mLefts);
            return new LeftSidedPair<>(nextLeft);
        }
        if (result > 0)
        {
            // return the smaller result, the right one in this case
            Optional<Right> nextRight = mNextRight;
            // advance right
            mNextRight = new Next<>(mRights);
            return new RightSidedPair<>(nextRight);
        }

        // both sides are equal
        Optional<Left> nextLeft = mNextLeft;
        Optional<Right> nextRight = mNextRight;
        // advance both
        mNextLeft = new Next<>(mLefts);
        mNextRight = new Next<>(mRights);
        return new ValuePair<>(nextLeft, nextRight);
    }

}
