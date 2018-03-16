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

import org.dmfs.jems.predicate.Predicate;
import org.dmfs.jems.single.Single;


/**
 * @author Marten Gajda
 */
public interface FluentPredicate<T> extends Predicate<T>
{
    FluentPredicate<T> and(Predicate<T> predicate);

    FluentPredicate<T> andAllOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> andAnyOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> andNoneOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> or(Predicate<T> predicate);

    FluentPredicate<T> orAllOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> orAnyOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> orNoneOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> nor(Predicate<T> predicate);

    FluentPredicate<T> norAllOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> norAnyOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> norNoneOf(Iterable<Predicate<T>> predicates);

    FluentPredicate<T> unless(Predicate<T> predicate);

    FluentPredicate<T> unlessAllOf(Iterable<Predicate<T>> predicate);

    FluentPredicate<T> unlessAnyOf(Iterable<Predicate<T>> predicate);

    FluentPredicate<T> unlessNoneOf(Iterable<Predicate<T>> predicate);

    FluentPredicate<Single<T>> singleValue();
}
