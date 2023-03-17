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

import org.dmfs.jems2.Optional;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;


@StaticFactories(value = "Jems2", packageName = "org.dmfs.jems2.confidence")
public final class Absent extends QualityComposition<Optional<?>>
{
    /**
     * Matches empty {@link Optional}s.
     */
    public Absent()
    {
        super(new Satisfies<>(
            actual -> !actual.isPresent(),
            actual -> new Spaced(new TextDescription("<present"), new ValueDescription(actual), new TextDescription(">")),
            new TextDescription("<absent>")));
    }
}
