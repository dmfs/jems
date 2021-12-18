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
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableEqualsMatcher.considersEqual;
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableOrderMatcher.imposesOrderOf;
import static org.junit.Assert.assertThat;


public class IterableComparatorTest
{
    @Test
    public void test()
    {
        assertThat(new IterableComparator<>(new OptionalComparator<>(naturalOrder())),
            imposesOrderOf(
                new EmptyIterable<>(),
                new Seq<>(1),
                new Seq<>(1, 2),
                new Seq<>(1, 3),
                new Seq<>(1, 3, 1),
                new Seq<>(1, 3, 2),
                new Seq<>(1, 4, 1),
                new Seq<>(4)));
    }


    @Test
    public void testShorterIsLarger()
    {
        assertThat(new IterableComparator<>(new GreaterAbsent<>(new OptionalComparator<>(naturalOrder()))),
            imposesOrderOf(
                new Seq<>(1, 2),
                new Seq<>(1, 3, 1),
                new Seq<>(1, 3, 2),
                new Seq<>(1, 3),
                new Seq<>(1, 4, 1),
                new Seq<>(1),
                new Seq<>(4),
                new EmptyIterable<>()));
    }


    @Test
    public void testEqual()
    {
        assertThat(new IterableComparator<>(new GreaterAbsent<>(new OptionalComparator<>(new By<>(String::length)))),
            considersEqual(
                new Seq<>("12", "123", "1234"),
                new Seq<>("ab", "abc", "abcd"),
                new Seq<>("--", "---", "----")));
    }
}