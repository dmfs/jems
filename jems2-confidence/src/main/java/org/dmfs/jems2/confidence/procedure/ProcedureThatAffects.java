/*
 * Copyright 2022 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.dmfs.jems2.confidence.procedure;

import org.dmfs.jems2.FragileProcedure;
import org.dmfs.jems2.Single;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.Core;
import org.saynotobugs.confidence.quality.grammar.SoIt;


@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class ProcedureThatAffects<T> implements Quality<FragileProcedure<T, ?>>
{
    private final Description mValueDescription;
    private final Single<T> mValueSupplier;
    private final Quality<? super T> mValueQuality;


    /**
     * A {@link Quality} that describes the side effect of a {@link FragileProcedure} on its argument.
     * <p>
     * The argument is provided by the given {@link Single} and tested by the given {@link Quality} after passing it to the consumer.
     * <p>
     * For best results, decorate the value{@link Quality} with {@link SoIt} or {@link Core#soIt(Quality)}.
     */
    public ProcedureThatAffects(Description valueDescription, Single<T> valueSupplier, Quality<? super T> valueQuality)
    {
        mValueDescription = valueDescription;
        mValueSupplier = valueSupplier;
        mValueQuality = valueQuality;
    }


    /**
     * A {@link Quality} that describes the side effect of a {@link FragileProcedure} on its argument.
     * <p>
     * The argument is provided by the given {@link Single} and tested by the given {@link Quality} after passing it to the consumer.
     * <p>
     * For best results, decorate the value{@link Quality} with {@link SoIt} or {@link Core#soIt(Quality)}.
     */
    public ProcedureThatAffects(Single<T> valueSupplier, Quality<? super T> valueQuality)
    {
        this(new Spaced(new TextDescription("affects"), new ValueDescription(valueSupplier.value())), valueSupplier, valueQuality);
    }


    @Override
    public Assessment assessmentOf(FragileProcedure<T, ?> candidate)
    {
        T value = mValueSupplier.value();
        try
        {
            candidate.process(value);
        }
        catch (Exception e)
        {
            return new Fail(new Spaced(
                new TextDescription("Procedure that threw"),
                new ValueDescription(e),
                new TextDescription("when called with"),
                new ValueDescription(value)));
        }
        return new FailPrepended(new Spaced(new TextDescription("Procedure that"), mValueDescription),
            mValueQuality.assessmentOf(value));
    }


    @Override
    public Description description()
    {
        return new Spaced(new TextDescription("Procedure that"), mValueDescription, mValueQuality.description());
    }
}
