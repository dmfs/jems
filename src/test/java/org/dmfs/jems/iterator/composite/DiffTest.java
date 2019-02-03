/*
 * Copyright 2019 dmfs GmbH
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

package org.dmfs.jems.iterator.composite;

import org.dmfs.iterators.EmptyIterator;
import org.dmfs.iterators.elementary.Seq;
import org.dmfs.jems.hamcrest.matchers.iterator.IteratorMatcher;
import org.dmfs.jems.pair.Pair;
import org.dmfs.optional.Optional;
import org.junit.Test;

import static org.dmfs.jems.hamcrest.matchers.AbsentMatcher.isAbsent;
import static org.dmfs.jems.hamcrest.matchers.PairMatcher.pair;
import static org.dmfs.jems.hamcrest.matchers.PresentMatcher.isPresent;
import static org.dmfs.jems.hamcrest.matchers.iterator.IteratorMatcher.iteratorOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link Diff}.
 *
 * @author Marten Gajda
 */
public class DiffTest
{
    @Test
    public void test()
    {
        assertThat(() -> new Diff<>(new EmptyIterator<>(), new EmptyIterator<>(), (l, r) -> 0),
                is(IteratorMatcher.<Pair<Optional<Object>, Optional<Object>>>emptyIterator()));

        assertThat(() -> new Diff<>(new Seq<>("a", "a", "b", "c"), new EmptyIterator<>(), (l, r) -> r.compareTo(l)),
                is(IteratorMatcher.<Pair<Optional<String>, Optional<String>>>iteratorOf(
                        pair(isPresent("a"), isAbsent()),
                        pair(isPresent("a"), isAbsent()),
                        pair(isPresent("b"), isAbsent()),
                        pair(isPresent("c"), isAbsent()))));

        assertThat(() -> new Diff<>(new EmptyIterator<>(), new Seq<>("a", "a", "b", "c"), (l, r) -> r.compareTo(l)),
                is(IteratorMatcher.<Pair<Optional<String>, Optional<String>>>iteratorOf(
                        pair(isAbsent(), isPresent("a")),
                        pair(isAbsent(), isPresent("a")),
                        pair(isAbsent(), isPresent("b")),
                        pair(isAbsent(), isPresent("c"))
                )));

        assertThat(() -> new Diff<>(new Seq<>("1", "2", "3", "4"), new Seq<>("a", "a", "b", "c"), (l, r) -> r.compareTo(l)),
                is(iteratorOf(
                        pair(isPresent("1"), isAbsent()),
                        pair(isPresent("2"), isAbsent()),
                        pair(isPresent("3"), isAbsent()),
                        pair(isPresent("4"), isAbsent()),
                        pair(isAbsent(), isPresent("a")),
                        pair(isAbsent(), isPresent("a")),
                        pair(isAbsent(), isPresent("b")),
                        pair(isAbsent(), isPresent("c"))
                )));

        assertThat(() -> new Diff<>(new Seq<>("a", "b", "b", "c"), new Seq<>("a", "a", "b", "c"), (l, r) -> r.compareTo(l)),
                is(IteratorMatcher.<Pair<Optional<String>, Optional<String>>>iteratorOf(
                        pair(isPresent("a"), isPresent("a")),
                        pair(isAbsent(), isPresent("a")),
                        pair(isPresent("b"), isPresent("b")),
                        pair(isPresent("b"), isAbsent()),
                        pair(isPresent("c"), isPresent("c"))
                )));

        assertThat(() -> new Diff<>(new Seq<>("b", "b", "c"), new Seq<>("a", "a", "b"), (l, r) -> r.compareTo(l)),
                is(IteratorMatcher.<Pair<Optional<String>, Optional<String>>>iteratorOf(
                        pair(isAbsent(), isPresent("a")),
                        pair(isAbsent(), isPresent("a")),
                        pair(isPresent("b"), isPresent("b")),
                        pair(isPresent("b"), isAbsent()),
                        pair(isPresent("c"), isAbsent())
                )));
    }
}