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

import org.dmfs.jems2.iterable.EmptyIterable;
import org.dmfs.jems2.iterable.Just;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.emptyIterator;
import static org.junit.Assert.assertThat;


public class JoinedTest
{
    @Test
    public void testTrivial()
    {
        assertThat(() -> new Joined<>(EmptyIterable.emptyIterable()), emptyIterator());
        assertThat(() -> new Joined<>(new Seq<>(EmptyIterable.emptyIterable())), emptyIterator());
    }


    @Test
    public void testSingle()
    {
        assertThat(() -> new Joined<>(new Seq<>(new Just<>("1"))),
            iteratesTo("1"));
    }


    @Test
    public void testMultiSingle()
    {
        assertThat(() -> new Joined<>(new Seq<>(new Just<>("1"), new Just<>("2"), new Just<>("3"))),
            iteratesTo("1", "2", "3"));
    }


    @Test
    public void testMulti()
    {
        assertThat(() -> new Joined<>(new Seq<>(new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"))),
            iteratesTo("1", "2", "3"));
        assertThat(() -> new Joined<>(new Seq<>(new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"), EmptyIterable.emptyIterable())),
            iteratesTo("1", "2", "3"));
        assertThat(() -> new Joined<>(
                new Seq<>(
                    EmptyIterable.emptyIterable(),
                    new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"),
                    EmptyIterable.emptyIterable())),
            iteratesTo("1", "2", "3"));
    }


    @Test
    public void testMultiMulti()
    {
        assertThat(() -> new Joined<>(
                new Seq<>(
                    new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"),
                    new org.dmfs.jems2.iterable.Seq<>("a", "b", "c"),
                    new org.dmfs.jems2.iterable.Seq<>("z", "zz", "zzz")
                )),
            iteratesTo("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(() -> new Joined<>(
                new Seq<>(
                    EmptyIterable.emptyIterable(),
                    new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"),
                    new org.dmfs.jems2.iterable.Seq<>("a", "b", "c"),
                    new org.dmfs.jems2.iterable.Seq<>("z", "zz", "zzz")
                )),
            iteratesTo("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(() -> new Joined<>(
                new Seq<>(
                    new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"),
                    new org.dmfs.jems2.iterable.Seq<>("a", "b", "c"),
                    new org.dmfs.jems2.iterable.Seq<>("z", "zz", "zzz"),
                    EmptyIterable.emptyIterable()
                )),
            iteratesTo("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));
    }


    @Test
    public void testVarArgCtor()
    {
        assertThat(() -> new Joined<>(
                new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"),
                new org.dmfs.jems2.iterable.Seq<>("a", "b", "c"),
                new org.dmfs.jems2.iterable.Seq<>("z", "zz", "zzz")
            ),
            iteratesTo("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(() -> new Joined<>(
                EmptyIterable.emptyIterable(),
                new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"),
                new org.dmfs.jems2.iterable.Seq<>("a", "b", "c"),
                new org.dmfs.jems2.iterable.Seq<>("z", "zz", "zzz")
            ),
            iteratesTo("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(() -> new Joined<>(
                new org.dmfs.jems2.iterable.Seq<>("1", "2", "3"),
                new org.dmfs.jems2.iterable.Seq<>("a", "b", "c"),
                new org.dmfs.jems2.iterable.Seq<>("z", "zz", "zzz"),
                EmptyIterable.emptyIterable()
            ),
            iteratesTo("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));
    }
}