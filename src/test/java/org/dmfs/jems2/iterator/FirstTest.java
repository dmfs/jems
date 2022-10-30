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

import org.dmfs.jems2.generatable.Sequence;
import org.dmfs.jems2.iterable.First;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.emptyIterator;
import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.iteratorOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


/**
 * Unit test for {@link org.dmfs.jems2.iterator.First}.
 */
public class FirstTest
{
    @Test
    public void testIterator()
    {
        assertThat(() -> new org.dmfs.jems2.iterator.First(0, EmptyIterator.emptyIterator()), is(emptyIterator()));
        assertThat(() -> new org.dmfs.jems2.iterator.First(0, new Seq<>(1)), is(emptyIterator()));
        assertThat(() -> new org.dmfs.jems2.iterator.First(0, new Seq<>(1, 2, 3)), is(emptyIterator()));
        assertThat(() -> new org.dmfs.jems2.iterator.First(-1, new Seq<>(1, 2, 3)), is(emptyIterator()));

        assertThat(() -> new org.dmfs.jems2.iterator.First(1, EmptyIterator.emptyIterator()), is(emptyIterator()));

        assertThat(() -> new org.dmfs.jems2.iterator.First(1, new Seq<>(1, 2, 3)), is(iteratorOf(1)));
        assertThat(() -> new org.dmfs.jems2.iterator.First(2, new Seq<>(1, 2, 3)), is(iteratorOf(1, 2)));
        assertThat(() -> new org.dmfs.jems2.iterator.First(3, new Seq<>(1, 2, 3)), is(iteratorOf(1, 2, 3)));
        assertThat(() -> new org.dmfs.jems2.iterator.First(4, new Seq<>(1, 2, 3)), is(iteratorOf(1, 2, 3)));
        assertThat(() -> new org.dmfs.jems2.iterator.First(5, new Seq<>(1, 2, 3)), is(iteratorOf(1, 2, 3)));
    }


    @Test
    public void testGenerator()
    {
        MatcherAssert.assertThat(new First<>(0, new Sequence<>(5, i -> i + 1)), is(emptyIterable()));
        MatcherAssert.assertThat(new First<>(1, new Sequence<>(5, i -> i + 1)), iteratesTo(5));
        MatcherAssert.assertThat(new First<>(5, new Sequence<>(5, i -> i + 1)), iteratesTo(5, 6, 7, 8, 9));
    }
}