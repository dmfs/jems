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

package org.dmfs.jems2.optional;

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Predicate;
import org.dmfs.jems2.iterable.Sieved;


/**
 * The first value of an {@link Iterable}.
 */
public final class First<T> extends LazyDelegatingOptional<T>
{
    /**
     * Creates an {@link Optional} of the first value of the given {@link Iterable} which matches the given {@link Predicate}.
     *
     * @param predicate
     *     The {@link Predicate}
     * @param iterable
     *     The {@link Iterable}
     */
    public First(Predicate<? super T> predicate, Iterable<T> iterable)
    {
        this(new Sieved<>(predicate, iterable));
    }


    /**
     * Creates the {@link Optional} first value of the given {@link Iterable}.
     *
     * @param iterable
     *     A {@link Iterable}.
     */
    public First(Iterable<T> iterable)
    {
        super(() -> new Next<>(iterable.iterator()));
    }
}
