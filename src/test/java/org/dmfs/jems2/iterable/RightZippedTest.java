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

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.single.Backed;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


public class RightZippedTest
{
    @Test
    public void testRegular()
    {
        assertThat(
            new RightZipped<Integer, String, String>(new EmptyIterable<>(), new EmptyIterable<>(), this::regular),
            is(emptyIterable()));

        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1), new EmptyIterable<>(), this::regular),
            is(emptyIterable()));
        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1, 2, 3), new EmptyIterable<>(), this::regular),
            is(emptyIterable()));
        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a", "b"), this::regular),
            iteratesTo("1a", "2b"));

        assertThat(
            new RightZipped<Integer, String, String>(new EmptyIterable<>(), new Seq<>("a"), this::regular),
            iteratesTo("-a"));
        assertThat(
            new RightZipped<Integer, String, String>(new EmptyIterable<>(), new Seq<>("a", "b", "c"), this::regular),
            iteratesTo("-a", "-b", "-c"));
        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1, 2), new Seq<>("a", "b", "c"), this::regular),
            iteratesTo("1a", "2b", "-c"));

        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a", "b", "c"), this::regular),
            iteratesTo("1a", "2b", "3c"));
    }


    @Test
    public void testIgnoreRight()
    {

        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a"), this::ignoreRight),
            iteratesTo("1"));
        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1), new Seq<>("a", "b", "c"), this::ignoreRight),
            iteratesTo("1", "-", "-"));

    }


    @Test
    public void testIgnoreLeft()
    {

        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a"), this::ignoreLeft),
            iteratesTo("a"));
        assertThat(
            new RightZipped<Integer, String, String>(new Seq<>(1), new Seq<>("a", "b", "c"), this::ignoreLeft),
            iteratesTo("a", "b", "c"));

    }


    private String regular(Optional<? extends Integer> i, String s)
    {
        return new Backed<>(i, "-").value() + s;
    }


    private String ignoreRight(Optional<? extends Integer> i, String s)
    {
        return new Backed<>(i, "-").value().toString();
    }


    private String ignoreLeft(Optional<? extends Integer> i, String s)
    {
        return s;
    }
}