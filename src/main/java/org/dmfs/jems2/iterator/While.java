package org.dmfs.jems2.iterator;

import org.dmfs.jems2.Predicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@link Iterator} that iterates the elements of the delegate while they match the given {@link Predicate} and stops
 * when this is no longer the case.
 * Note, this will not necessarily iterate all elements of the delegate.
 */
public final class While<T> extends BaseIterator<T>
{
    private final Predicate<? super T> mPredicate;
    private final Iterator<? extends T> mDelegate;
    public T mNext;
    private boolean mDeterminedNext;
    private boolean mDone;

    public While(Predicate<? super T> predicate, Iterator<? extends T> delegate)
    {
        mPredicate = predicate;
        mDelegate = delegate;
    }

    @Override
    public boolean hasNext()
    {
        return moveToNext();
    }

    @Override
    public T next()
    {
        if (!moveToNext())
        {
            throw new NoSuchElementException("No more elements to iterate");
        }
        mDeterminedNext = false;
        return mNext;
    }


    /**
     * Ensures mNext has the value of the next element to return.
     */
    private boolean moveToNext()
    {
        if (mDone || mDeterminedNext)
        {
            return !mDone;
        }

        if (mDelegate.hasNext())
        {
            T next = mDelegate.next();
            if (mPredicate.satisfiedBy(next))
            {
                mNext = next;
                mDeterminedNext = true;
                return true;
            }
        }
        mDone = true;
        return false;
    }
}
