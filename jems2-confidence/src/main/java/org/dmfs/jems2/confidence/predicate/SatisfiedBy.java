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

package org.dmfs.jems2.confidence.predicate;

import org.dmfs.jems2.Predicate;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.description.*;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class SatisfiedBy<T> extends QualityComposition<Predicate<T>>
{
    public SatisfiedBy(T testee)
    {
        super(new Has<>(
            (Description orig) -> new Spaced(new Text("satisfied by"), new Value(testee)),
            orig -> new Spaced(new Text("not satisfied by"), new Value(testee)),
            actual -> actual.satisfiedBy(testee),
            new EqualTo<>(true)));
    }
}
