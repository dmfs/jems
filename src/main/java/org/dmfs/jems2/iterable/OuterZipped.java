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
 * In contrast to {@link Zipped}, this returns as many results as the longer of the two given {@link Iterable}s.
 * The given {@link BiFunction} receives {@link Optional}s, which are absent for the shorter {@link Iterable} when it runs out of elements.
 */
public final class OuterZipped<Left, Right, Result> implements Iterable<Result>
{
    private final Iterable<? extends Left> mLeft;
    private final Iterable<? extends Right> mRight;
    private final BiFunction<? super Optional<? extends Left>, ? super Optional<? extends Right>, ? extends Result> mZipFunction;


    public OuterZipped(
        Iterable<? extends Left> left,
        Iterable<? extends Right> right,
        BiFunction<? super Optional<? extends Left>, ? super Optional<? extends Right>, ? extends Result> zipFunction)
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
                return left.hasNext() || right.hasNext();
            }


            @Override
            public Result next()
            {
                return mZipFunction.value(left.hasNext() ? new Present<>(left.next()) : absent(), right.hasNext() ? new Present<>(right.next()) : absent());
            }
        };
    }
}
