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

import org.dmfs.jems2.iterable.EmptyIterable;
import org.dmfs.jems2.iterable.Seq;
import org.junit.Test;

import static java.util.Comparator.naturalOrder;
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableOrderMatcher.imposesOrderOf;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class IterableComparatorTest
{
    @Test
    public void testBothEmpty()
    {
        assertThat(new IterableComparator<>((l, r) -> -1).compare(new EmptyIterable<>(), new EmptyIterable<>()), is(equalTo(0)));
    }


    @Test
    public void test()
    {
        assertThat(new IterableComparator<>(new OptionalComparator<>(naturalOrder())),
            imposesOrderOf(
                new EmptyIterable<>(),
                new Seq<>(1),
                new Seq<>(1,2),
                new Seq<>(1,3),
                new Seq<>(4)));
    }

    @Test
    public void testOneEmpty()
    {
        assertThat(
            new IterableComparator<>(new OptionalComparator<>((l, r) -> -1)).compare(new Seq<>(1, 2, 3), new EmptyIterable<>()),
            is(greaterThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<>((l, r) -> 1)).compare(new Seq<>(1, 2, 3), new EmptyIterable<>()),
            is(greaterThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<>((l, r) -> -1)).compare(new EmptyIterable<>(), new Seq<>(1, 2, 3)),
            is(lessThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<>((l, r) -> 1)).compare(new EmptyIterable<>(), new Seq<>(1, 2, 3)),
            is(lessThan(0)));
    }


    @Test
    public void testSameLength()
    {
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1), new Seq<>(1)),
            is(equalTo(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 2, 3), new Seq<>(1, 2, 3)),
            is(equalTo(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1), new Seq<>(2)),
            is(lessThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(2), new Seq<>(1)),
            is(greaterThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 2, 3), new Seq<>(1, 2, 4)),
            is(lessThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 2, 4), new Seq<>(1, 2, 3)),
            is(greaterThan(0)));
    }


    @Test
    public void testDifferentLength()
    {
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1), new Seq<>(1, 2, 3)),
            is(lessThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 2, 3), new Seq<>(1)),
            is(greaterThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 2), new Seq<>(1, 2, 3)),
            is(lessThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 2, 3), new Seq<>(1, 2)),
            is(greaterThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 3), new Seq<>(1, 2, 3)),
            is(greaterThan(0)));
        assertThat(
            new IterableComparator<>(new OptionalComparator<Integer>(naturalOrder())).compare(new Seq<>(1, 2, 3), new Seq<>(1, 3)),
            is(lessThan(0)));
    }
}