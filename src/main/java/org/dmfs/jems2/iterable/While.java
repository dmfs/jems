package org.dmfs.jems2.iterable;

import org.dmfs.jems2.Predicate;

/**
 * An {@link Iterable} that iterates the elements of the delegate while they match the given {@link Predicate} and stops
 * when this is no longer the case.
 * Note, this will not necessarily iterate all elements of the delegate.
 */
public final class While<T> extends DelegatingIterable<T>
{
    public While(Predicate<? super T> predicate, Iterable<T> delegate)
    {
        super(() -> new org.dmfs.jems2.iterator.While<>(predicate, delegate.iterator()));
    }
}
