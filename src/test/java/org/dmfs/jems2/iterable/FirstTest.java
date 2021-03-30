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

package org.dmfs.jems2.iterable;

import org.dmfs.jems2.generatable.Sequence;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


/**
 * Unit test for {@link First}.
 */
public class FirstTest
{
    @Test
    public void testIterables()
    {
        assertThat(new First<>(EmptyIterable.emptyIterable()), is(emptyIterable()));
        assertThat(new First<>(new Seq<>(3, 2, 1)), iteratesTo(3));

        assertThat(new First<>(0, EmptyIterable.emptyIterable()), is(emptyIterable()));
        assertThat(new First<>(-1, EmptyIterable.emptyIterable()), is(emptyIterable()));
        assertThat(new First<>(1, EmptyIterable.emptyIterable()), is(emptyIterable()));
        assertThat(new First<>(0, new Seq<>(3, 2, 1)), is(emptyIterable()));
        assertThat(new First<>(-1, new Seq<>(3, 2, 1)), is(emptyIterable()));

        assertThat(new First<>(1, new Seq<>(3, 2, 1)), iteratesTo(3));
        assertThat(new First<>(2, new Seq<>(3, 2, 1)), iteratesTo(3, 2));
        assertThat(new First<>(3, new Seq<>(3, 2, 1)), iteratesTo(3, 2, 1));
        assertThat(new First<>(4, new Seq<>(3, 2, 1)), iteratesTo(3, 2, 1));
        assertThat(new First<>(5, new Seq<>(3, 2, 1)), iteratesTo(3, 2, 1));
    }


    @Test
    public void testGeneratable()
    {
        assertThat(new First<>(new Sequence<>(5, i -> i + 1)), iteratesTo(5));

        assertThat(new First<>(0, new Sequence<>(5, i -> i + 1)), is(emptyIterable()));
        assertThat(new First<>(1, new Sequence<>(5, i -> i + 1)), iteratesTo(5));
        assertThat(new First<>(5, new Sequence<>(5, i -> i + 1)), iteratesTo(5, 6, 7, 8, 9));
    }
}