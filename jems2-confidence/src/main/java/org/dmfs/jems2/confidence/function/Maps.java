package org.dmfs.jems2.confidence.function;

import org.dmfs.jems2.FragileFunction;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class Maps<Argument, Result> extends QualityComposition<FragileFunction<Argument, Result, ?>>
{
    public Maps(Argument argument, Result result)
    {
        this(argument, new EqualTo<>(result));
    }


    public Maps(Argument argument, Quality<? super Result> resultQuality)
    {
        super(new Has<>(
            new Spaced(new TextDescription("maps"), new ValueDescription(argument)),
            new Spaced(new TextDescription("mapped"), new ValueDescription(argument)),
            candidate -> candidate.value(argument),
            resultQuality
        ));
    }
}
