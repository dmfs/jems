package org.dmfs.jems2.confidence.fragile;

import org.dmfs.jems2.Fragile;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.InstanceOf;


/**
 * {@link Quality} of {@link Fragile} instances throwing an {@link Exception}.
 */
@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class Throwing extends QualityComposition<Fragile<?, ?>>
{
    public Throwing(Class<? extends Throwable> throwable)
    {
        this(new InstanceOf<>(throwable));
    }


    public Throwing(Quality<? super Throwable> delegate)
    {
        super(new Has<>("value()",
            fragile -> fragile::value,
            new org.saynotobugs.confidence.quality.object.Throwing(delegate)));
    }
}
