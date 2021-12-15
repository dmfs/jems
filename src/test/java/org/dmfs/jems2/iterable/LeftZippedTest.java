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
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class LeftZippedTest
{
    @Test
    public void testRegular()
    {
        assertThat(
            new LeftZipped<Integer, String, String>(new EmptyIterable<>(), new EmptyIterable<>(), this::regular),
            is(emptyIterable()));

        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1), new EmptyIterable<>(), this::regular),
            iteratesTo("1-"));
        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1, 2, 3), new EmptyIterable<>(), this::regular),
            iteratesTo("1-", "2-", "3-"));
        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a", "b"), this::regular),
            iteratesTo("1a", "2b", "3-"));

        assertThat(
            new LeftZipped<Integer, String, String>(new EmptyIterable<>(), new Seq<>("a"), this::regular),
            is(emptyIterable()));
        assertThat(
            new LeftZipped<Integer, String, String>(new EmptyIterable<>(), new Seq<>("a", "b", "c"), this::regular),
            is(emptyIterable()));
        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1, 2), new Seq<>("a", "b", "c"), this::regular),
            iteratesTo("1a", "2b"));

        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a", "b", "c"), this::regular),
            iteratesTo("1a", "2b", "3c"));
    }


    @Test
    public void testIgnoreRight()
    {

        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a"), this::ignoreRight),
            iteratesTo("1", "2", "3"));
        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1), new Seq<>("a", "b", "c"), this::ignoreRight),
            iteratesTo("1"));

    }


    @Test
    public void testIgnoreLeft()
    {

        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1, 2, 3), new Seq<>("a"), this::ignoreLeft),
            iteratesTo("a", "-", "-"));
        assertThat(
            new LeftZipped<Integer, String, String>(new Seq<>(1), new Seq<>("a", "b", "c"), this::ignoreLeft),
            iteratesTo("a"));

    }


    private String regular(Integer i, Optional<? extends String> s)
    {
        return i + new Backed<>(s, "-").value();
    }


    private String ignoreRight(Integer i, Optional<? extends String> s)
    {
        return i.toString();
    }


    private String ignoreLeft(Integer i, Optional<? extends String> s)
    {
        return new Backed<>(s, "-").value();
    }
}