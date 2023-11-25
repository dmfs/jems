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

package org.dmfs.jems2.confidence.optional;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Optional;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.Text;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.quality.trivial.Anything;


@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class Present<T> extends QualityComposition<Optional<T>>
{
    /**
     * Matches present {@link Optional}s ith any value.
     */
    public Present()
    {
        this(new Anything());
    }


    /**
     * Matches present {@link Optional}s with a value that's equal to the given one.
     */
    public Present(T value)
    {
        this(new EqualTo<>(value));
    }


    /**
     * Matches present {@link Optional}s with a value that matches the given {@link Quality}.
     */
    public Present(Quality<? super T> delegate)
    {
        this(description -> new Spaced(new Text("present"), description),
            description -> new Spaced(new Text("present"), description),
            new Text("absent"),
            delegate);
    }


    /**
     * Matches present {@link Optional}s with a value that matches the given {@link Quality}.
     * <p>
     * This constructor takes additional arguments to customize the {@link Description}s of the {@link Quality}.
     */
    public Present(Function<? super Description, ? extends Description> expectationDescription,
        Function<? super Description, ? extends Description> delegateFailDescription,
        Description absentDescription,
        Quality<? super T> delegate)
    {
        super(actual -> actual.isPresent()
                ? new Has<Optional<T>, T>(expectationDescription::value, delegateFailDescription::value, Optional::value, delegate).assessmentOf(actual)
                : new Fail(absentDescription),
            expectationDescription.value(delegate.description()));
    }
}
