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

package org.dmfs.jems2.comparator;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Backed;

import java.util.Comparator;


/**
 * A {@link Comparator} which delegates the comparison of two instances to other {@link Comparator}s until the first one returns a non-zero result.
 * <p>
 * The result is the same as repeatedly calling "thenComparing" on the {@link Comparator}s, i.e. the following two expressions return the same Comparator:
 * <pre>
 *     comparatorA.thenComparing(comparatorB).thenComparing(comparatorC)
 *
 *     new Composite&lt;&gt;(comparatorA, comparatorB, comparatorC)
 * </pre>
 */
public final class Composite<T> extends DelegatingComparator<T>
{
    @SafeVarargs
    public Composite(Comparator<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Composite(Iterable<? extends Comparator<? super T>> delegates)
    {
        super((left, right) -> new Backed<>(
            new First<>(
                result -> result != 0,
                new Mapped<>(comparator -> comparator.compare(left, right), delegates)),
            0).value());
    }
}
