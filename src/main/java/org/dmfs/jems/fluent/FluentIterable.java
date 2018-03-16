/*
 * Copyright 2018 dmfs GmbH
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

package org.dmfs.jems.fluent;

import org.dmfs.jems.function.BiFunction;
import org.dmfs.jems.function.Function;
import org.dmfs.jems.pair.Pair;
import org.dmfs.jems.predicate.Predicate;


/**
 * @author Marten Gajda
 */
public interface FluentIterable<T> extends Iterable<T>
{
    <V> FluentIterable<V> mapped(Function<T, V> function);

    FluentIterable<T> sieved(Predicate<T> predicate);

    <V> FluentIterable<V> expanded(Function<T, Iterable<V>> expansionFunction);

    FluentIterable<T> joinedWith(Iterable<Iterable<T>> iterable);

    FluentIterable<T> joinedTo(Iterable<Iterable<T>> iterable);

    FluentIterable<T> reversed();

    FluentIterable<T> distinct();

    <V, R> FluentIterable<R> zipped(Iterable<V> other, BiFunction<T, V, R> zipFunction);

    <V> FluentIterable<Pair<T, V>> paired(Iterable<V> other);

    FluentIterable<Pair<Integer, T>> numbered();

    FluentOptional<T> first();

    FluentOptional<T> firstThat(Predicate<T> function);

}
