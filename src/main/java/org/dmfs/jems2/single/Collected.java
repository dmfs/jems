/*
 * Copyright 2021 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.jems2.single;

import org.dmfs.jems2.Generator;
import org.dmfs.jems2.Single;


/**
 * A {@link Single} holding the collected values of an {@link Iterable}.
 * <p>
 * This expects a generator which creates the initial (usually empty) collection and an Iterable which contains the values to collect.
 * <p>
 * Example
 * <pre>{@code
 * List<String> valueList = new Collected<>(ArrayList::new, values).value();
 * Set<String> valueSet  = new Collected<>(HashSet::new, values).value();
 * }</pre>
 */
public final class Collected<Value, Collection extends java.util.Collection<Value>> extends DelegatingSingle<Collection>
{
    public Collected(Generator<? extends Collection> collectionGenerator, Iterable<? extends Value> values)
    {
        super(new Reduced<>(collectionGenerator, (collection, value) -> {
            collection.add(value);
            return collection;
        }, values));
    }
}
