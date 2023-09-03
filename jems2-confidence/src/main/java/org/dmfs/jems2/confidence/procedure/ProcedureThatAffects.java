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
import org.saynotobugs.confidence.assessment.AllPassed;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.Text;
import org.saynotobugs.confidence.description.Value;
import org.saynotobugs.confidence.quality.Core;
import org.saynotobugs.confidence.quality.grammar.SoIt;
import org.saynotobugs.confidence.quality.object.Throwing;

import static org.saynotobugs.confidence.description.LiteralDescription.EMPTY;
import static org.saynotobugs.confidence.description.LiteralDescription.SPACE;


@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class ProcedureThatAffects<T> implements Quality<FragileProcedure<T, ?>>
{
    private final Description mValueDescription;
    private final Single<T> mValueSupplier;
    private final Quality<? super T> mValueQuality;
    private final Quality<Throwing.Breakable> mCallQuality;

    /**
     * A {@link Quality} that describes the side effect of a {@link FragileProcedure} on its argument.
     * <p>
     * The argument is provided by the given {@link Single} and tested by the given {@link Quality} after passing it to the consumer.
     * <p>
     * For best results, decorate the value{@link Quality} with {@link SoIt} or {@link Core#soIt(Quality)}.
     */
    public ProcedureThatAffects(Description valueDescription, Single<T> valueSupplier, Quality<? super T> valueQuality)
    {
        this(valueDescription, valueSupplier, valueQuality, new Runs(new Text("after successful execution")));
    }

    /**
     * A {@link Quality} that describes the side effect of a {@link FragileProcedure} on its argument.
     * <p>
     * The argument is provided by the given {@link Single} and tested by the given {@link Quality} after passing it to the consumer.
     * <p>
     * For best results, decorate the value{@link Quality} with {@link SoIt} or {@link Core#soIt(Quality)}.
     */
    public ProcedureThatAffects(Description valueDescription, Single<T> valueSupplier, Quality<? super T> valueQuality, Quality<Throwing.Breakable> callQuality)
    {
        mValueDescription = valueDescription;
        mValueSupplier = valueSupplier;
        mValueQuality = valueQuality;
        mCallQuality = callQuality;
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
        this(new Spaced(new Text("affects"), new Value(valueSupplier.value())), valueSupplier, valueQuality);
    }


    @Override
    public Assessment assessmentOf(FragileProcedure<T, ?> candidate)
    {
        T value = mValueSupplier.value();

        Assessment callAssessment = mCallQuality.assessmentOf(() -> candidate.process(value));
        Assessment valueAssessment = mValueQuality.assessmentOf(value);

        return new AllPassed(new Spaced(new Text("Procedure that"), mValueDescription, EMPTY), SPACE, valueAssessment, callAssessment);
    }


    @Override
    public Description description()
    {
        return new Spaced(new Text("Procedure that"), mValueDescription, mValueQuality.description(), mCallQuality.description());
    }
}
