package org.dmfs.jems2.iterable;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Optional;
import org.dmfs.jems2.optional.Present;

import java.util.Iterator;

import static org.dmfs.jems2.optional.Absent.absent;


/**
 * /**
 * An {@link Iterable} combining the elements of two given {@link Iterable}s using a {@link BiFunction}.
 * <p>
 * In contrast to {@link Zipped}, this always returns as many results as the right {@link Iterable}.
 * The given {@link BiFunction} receives an {@link Optional} as the first argument, which is absent in case the left {@link Iterable} has run out
 * of elements.
 * <p>
 * If the left {@link Iterable} has more elements than the right one, the excess elements are not iterated.
 */
public final class RightZipped<Left, Right, Result> implements Iterable<Result>
{
    private final Iterable<? extends Left> mLeft;
    private final Iterable<? extends Right> mRight;
    private final BiFunction<? super Optional<? extends Left>, ? super Right, ? extends Result> mZipFunction;


    public RightZipped(
        Iterable<? extends Left> left,
        Iterable<? extends Right> right,
        BiFunction<? super Optional<? extends Left>, ? super Right, ? extends Result> zipFunction)
    {
        mLeft = left;
        mRight = right;
        mZipFunction = zipFunction;
    }


    @Override
    public Iterator<Result> iterator()
    {
        Iterator<? extends Left> left = mLeft.iterator();
        Iterator<? extends Right> right = mRight.iterator();
        return new Iterator<Result>()
        {
            @Override
            public boolean hasNext()
            {
                return right.hasNext();
            }


            @Override
            public Result next()
            {
                return mZipFunction.value(left.hasNext() ? new Present<>(left.next()) : absent(), right.next());
            }
        };
    }
}
