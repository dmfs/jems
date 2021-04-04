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

package org.dmfs.jems2.iterator;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Predicate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * An {@link Iterator} which drops duplicates of already iterated elements.
 * <p>
 * Examples
 *
 * <pre>
 * Distinct([1, 2, 2, 1, 2, 3, 3]) -&gt;
 * [1, 2, 3]
 *
 * Distinct(String::length, ["abc", "xyz", "42", "21"]) -&gt;
 * ["abc", "42"]
 * </pre>
 */
public final class Distinct<T> extends DelegatingIterator<T>
{
    public Distinct(Iterator<T> delegate)
    {
        this(x -> x, delegate);
    }


    public <V> Distinct(Function<? super T, ? extends V> criterionFunction, Iterator<T> delegate)
    {
        super(new Sieved<>(
            // TODO: this violates the Predicate contract -> find a better solution
            new Predicate<T>()
            {
                private final Set<V> iterated = new HashSet<>();


                @Override
                public boolean satisfiedBy(T testedInstance)
                {
                    return iterated.add(criterionFunction.value(testedInstance));
                }
            },
            delegate));
    }
}
