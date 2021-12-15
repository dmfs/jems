package org.dmfs.jems2.comparator;

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.iterable.OuterZipped;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Backed;

import java.util.Comparator;


/**
 * Comparator for {@link Iterable}s. Elements are compared by the given {@link Comparator} of {@link Optional}s. The delegate will receive absent
 * {@link Optional}s when the shorter {@link Iterable} has run out of elements.
 *
 * <h2>Examples</h2>
 * <p>
 * With new <code>OptionalComparator(naturalOrder)</code> as the delegate
 * <pre>
 * [] == []
 * [1, 2, 3] == [1, 2, 3]
 * [1, 2, 3] &lt; [1, 2, 3, 4]
 * [1, 2, 3] &lt; [1, 2, 4]
 * [1, 2, 3] &lt; [1, 3]
 * </pre>
 */
public final class IterableComparator<T> extends DelegatingComparator<Iterable<? extends T>>
{
    public IterableComparator(Comparator<? super Optional<? extends T>> delegate)
    {
        super((l, r) -> new Backed<>(new First<>(c -> c != 0, new OuterZipped<T, T, Integer>(l, r, delegate::compare)), 0).value());
    }
}
