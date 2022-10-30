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

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.emptyIterator;
import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.iteratorOf;
import static org.dmfs.jems2.hamcrest.matchers.pair.PairMatcher.pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Test {@link Paired}.
 */
public class PairedTest
{
    @Test
    public void test()
    {
        assertThat(
            () -> new Paired<>(EmptyIterator.emptyIterator(), EmptyIterator.emptyIterator()),
            is(emptyIterator()));

        assertThat(
            () -> new Paired<>(new Seq<>(1, 2, 3), EmptyIterator.emptyIterator()),
            is(emptyIterator()));

        assertThat(
            () -> new Paired<>(EmptyIterator.emptyIterator(), new Seq<>(1, 2, 3)),
            is(emptyIterator()));

        assertThat(
            () -> new Paired<>(new Seq<>(1, 2, 3), new Seq<>(4, 5, 6, 7, 8)),
            is(iteratorOf(
                pair(1, 4),
                pair(2, 5),
                pair(3, 6))));

        assertThat(
            () -> new Paired<>(new Seq<>(4, 5, 6, 7, 8), new Seq<>(1, 2, 3)),
            is(iteratorOf(
                pair(4, 1),
                pair(5, 2),
                pair(6, 3))));

        assertThat(
            () -> new Paired<>(new Seq<>(4, 5, 6), new Seq<>(1, 2, 3)),
            is(iteratorOf(
                pair(4, 1),
                pair(5, 2),
                pair(6, 3))));
    }
}