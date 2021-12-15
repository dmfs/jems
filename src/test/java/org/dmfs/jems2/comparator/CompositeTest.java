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

import java.util.Comparator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class CompositeTest
{
    @Test
    public void testEmpty()
    {
        assertThat(new Composite<>().compare(1, 2), is(equalTo(0)));
        assertThat(new Composite<>().compare(2, 1), is(equalTo(0)));
        assertThat(new Composite<>().compare(1, 1), is(equalTo(0)));

        assertThat(new Composite<>(new EmptyIterable<>()).compare(1, 2), is(equalTo(0)));
        assertThat(new Composite<>(new EmptyIterable<>()).compare(2, 1), is(equalTo(0)));
        assertThat(new Composite<>(new EmptyIterable<>()).compare(1, 1), is(equalTo(0)));
    }


    @Test
    public void testSingle()
    {
        assertThat(new Composite<Integer>(Comparator.naturalOrder()).compare(1, 2), is(lessThan(0)));
        assertThat(new Composite<Integer>(Comparator.naturalOrder()).compare(2, 1), is(greaterThan(0)));
        assertThat(new Composite<Integer>(Comparator.naturalOrder()).compare(1, 1), is(equalTo(0)));

        assertThat(new Composite<Integer>(new Seq<>(Comparator.naturalOrder())).compare(1, 2), is(lessThan(0)));
        assertThat(new Composite<Integer>(new Seq<>(Comparator.naturalOrder())).compare(2, 1), is(greaterThan(0)));
        assertThat(new Composite<Integer>(new Seq<>(Comparator.naturalOrder())).compare(1, 1), is(equalTo(0)));
    }


    @Test
    public void testMultipleEarlyDifference()
    {
        assertThat(new Composite<Integer>((l, r) -> -1, Comparator.naturalOrder()).compare(1, 2), is(lessThan(0)));
        assertThat(new Composite<Integer>((l, r) -> -1, Comparator.reverseOrder()).compare(2, 1), is(lessThan(0)));
        assertThat(new Composite<Integer>((l, r) -> -1, Comparator.reverseOrder()).compare(1, 1), is(lessThan(0)));

        assertThat(new Composite<Integer>(new Seq<>((l, r) -> -1, Comparator.naturalOrder())).compare(1, 2), is(lessThan(0)));
        assertThat(new Composite<Integer>(new Seq<>((l, r) -> -1, Comparator.reverseOrder())).compare(2, 1), is(lessThan(0)));
        assertThat(new Composite<Integer>(new Seq<>((l, r) -> -1, Comparator.reverseOrder())).compare(1, 1), is(lessThan(0)));
    }


    @Test
    public void testMultipleLateDifference()
    {
        assertThat(new Composite<Integer>((l, r) -> 0, Comparator.naturalOrder()).compare(1, 2), is(lessThan(0)));
        assertThat(new Composite<Integer>((l, r) -> 0, Comparator.naturalOrder()).compare(2, 1), is(greaterThan(0)));
        assertThat(new Composite<Integer>((l, r) -> 0, Comparator.naturalOrder()).compare(1, 1), is(equalTo(0)));

        assertThat(new Composite<Integer>(new Seq<>((l, r) -> 0, Comparator.naturalOrder())).compare(1, 2), is(lessThan(0)));
        assertThat(new Composite<Integer>(new Seq<>((l, r) -> 0, Comparator.naturalOrder())).compare(2, 1), is(greaterThan(0)));
        assertThat(new Composite<Integer>(new Seq<>((l, r) -> 0, Comparator.naturalOrder())).compare(1, 1), is(equalTo(0)));
    }


    @Test
    public void testMultipleNoDifference()
    {
        assertThat(new Composite<Integer>((l, r) -> 0, (l, r) -> 0).compare(1, 2), is(equalTo(0)));
        assertThat(new Composite<Integer>((l, r) -> 0, (l, r) -> 0).compare(2, 1), is(equalTo(0)));
        assertThat(new Composite<Integer>((l, r) -> 0, (l, r) -> 0).compare(1, 1), is(equalTo(0)));

        assertThat(new Composite<Integer>(new Seq<>((l, r) -> 0, (l, r) -> 0)).compare(1, 2), is(equalTo(0)));
        assertThat(new Composite<Integer>(new Seq<>((l, r) -> 0, (l, r) -> 0)).compare(2, 1), is(equalTo(0)));
        assertThat(new Composite<Integer>(new Seq<>((l, r) -> 0, (l, r) -> 0)).compare(1, 1), is(equalTo(0)));
    }
}