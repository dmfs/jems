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

import org.dmfs.jems2.optional.Absent;
import org.dmfs.jems2.optional.Present;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Test {@link Joined}.
 */
public class JoinedTest
{
    @Test
    public void testTrivial()
    {
        assertThat(new Joined<>(EmptyIterable.emptyIterable()), emptyIterableOf(String.class));
        assertThat(new Joined<>(new Seq<>(EmptyIterable.emptyIterable())), emptyIterableOf(String.class));
    }


    @Test
    public void testSingle()
    {
        assertThat(new Joined<>(new Seq<>(new SingletonIterable<>("1"))), contains("1"));
    }


    @Test
    public void testMultiSingle()
    {
        assertThat(new Joined<>(new Seq<>(new SingletonIterable<>("1"), new SingletonIterable<>("2"), new SingletonIterable<>("3"))),
            contains("1", "2", "3"));
    }


    @Test
    public void testMulti()
    {
        assertThat(new Joined<>(new Seq<>(new Seq<>("1", "2", "3"))), contains("1", "2", "3"));
        assertThat(new Joined<>(new Seq<>(new Seq<>("1", "2", "3"), EmptyIterable.emptyIterable())), contains("1", "2", "3"));
        assertThat(new Joined<>(new Seq<>(EmptyIterable.emptyIterable(), new Seq<>("1", "2", "3"), EmptyIterable.emptyIterable())),
            contains("1", "2", "3"));
    }


    @Test
    public void testMultiMulti()
    {
        assertThat(new Joined<>(
                new Seq<>(
                    new Seq<>("1", "2", "3"),
                    new Seq<>("a", "b", "c"),
                    new Seq<>("z", "zz", "zzz")
                )),
            contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(new Joined<>(
                new Seq<>(
                    EmptyIterable.emptyIterable(),
                    new Seq<>("1", "2", "3"),
                    new Seq<>("a", "b", "c"),
                    new Seq<>("z", "zz", "zzz")
                )),
            contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(new Joined<>(
                new Seq<>(
                    new Seq<>("1", "2", "3"),
                    new Seq<>("a", "b", "c"),
                    new Seq<>("z", "zz", "zzz"),
                    EmptyIterable.emptyIterable()
                )),
            contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));
    }


    @Test
    public void testVarArgCtor()
    {
        assertThat(new Joined<>(
                new Seq<>("1", "2", "3"),
                new Seq<>("a", "b", "c"),
                new Seq<>("z", "zz", "zzz")
            ),
            contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(new Joined<>(
                EmptyIterable.emptyIterable(),
                new Seq<>("1", "2", "3"),
                new Seq<>("a", "b", "c"),
                new Seq<>("z", "zz", "zzz")
            ),
            contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(new Joined<>(
                new Seq<>("1", "2", "3"),
                new Seq<>("a", "b", "c"),
                new Seq<>("z", "zz", "zzz"),
                EmptyIterable.emptyIterable()
            ),
            contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));
    }


    @Test
    public void testVarArgOptionalCtor()
    {
        assertThat(new Joined<>(
                new Absent<>()
            ),
            is(emptyIterable()));

        assertThat(new Joined<>(
                new Absent<>(),
                new Absent<>(),
                new Absent<>()
            ),
            is(emptyIterable()));

        assertThat(new Joined<>(
                new Present<>(new EmptyIterable<>())
            ),
            is(emptyIterable()));

        assertThat(new Joined<>(
                new Present<>(new EmptyIterable<>()),
                new Present<>(new EmptyIterable<>()),
                new Present<>(new EmptyIterable<>())
            ),
            is(emptyIterable()));

        assertThat(new Joined<>(
                new Present<>(new EmptyIterable<>()),
                new Absent<>(),
                new Present<>(new EmptyIterable<>())
            ),
            is(emptyIterable()));

        assertThat(new Joined<>(
                new Present<>(new Seq<>(1, 2, 3))
            ),
            iteratesTo(1, 2, 3));

        assertThat(new Joined<>(
                new Present<>(new Seq<>(1, 2, 3)),
                new Present<>(new Seq<>(4, 5, 6)),
                new Present<>(new Seq<>(7, 8, 9))
            ),
            iteratesTo(1, 2, 3, 4, 5, 6, 7, 8, 9));

        assertThat(new Joined<>(
                new Absent<>(),
                new Present<>(new Seq<>(4, 5, 6)),
                new Present<>(new Seq<>(7, 8, 9))
            ),
            iteratesTo(4, 5, 6, 7, 8, 9));
    }


    @Test
    public void testConvenienceCtors()
    {
        assertThat(
            new Joined<>(new EmptyIterable<>(), new String[0]),
            is(emptyIterable()));

        assertThat(
            new Joined<>(new EmptyIterable<>(), "a"),
            iteratesTo("a"));

        assertThat(
            new Joined<>(new EmptyIterable<>(), "a", "b", "c"),
            iteratesTo("a", "b", "c"));

        assertThat(
            new Joined<>(new Seq<>("1"), new String[0]),
            is(iteratesTo("1")));

        assertThat(
            new Joined<>(new Seq<>("1"), "a"),
            iteratesTo("1", "a"));

        assertThat(
            new Joined<>(new Seq<>("1"), "a", "b", "c"),
            iteratesTo("1", "a", "b", "c"));

        assertThat(
            new Joined<>(new Seq<>("1", "2", "3"), new String[0]),
            is(iteratesTo("1", "2", "3")));

        assertThat(
            new Joined<>(new Seq<>("1", "2", "3"), "a"),
            iteratesTo("1", "2", "3", "a"));

        assertThat(
            new Joined<>(new Seq<>("1", "2", "3"), "a", "b", "c"),
            iteratesTo("1", "2", "3", "a", "b", "c"));
    }
}