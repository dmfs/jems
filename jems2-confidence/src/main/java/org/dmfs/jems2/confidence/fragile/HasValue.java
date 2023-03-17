package org.dmfs.jems2.confidence.fragile;

import org.dmfs.jems2.Fragile;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


/**
 * {@link Quality} of {@link Fragile} instances that provide specific value.
 */
@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class HasValue<T> extends QualityComposition<Fragile<T, ?>>
{
    public HasValue(T value)
    {
        this(new EqualTo<>(value));
    }


    public HasValue(Quality<? super T> delegate)
    {
        super(new Has<>("value", Fragile::value, delegate));
    }
}
