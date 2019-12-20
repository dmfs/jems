/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.jems.iterable.composite;

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.iterables.SingletonIterable;
import org.dmfs.jems.iterable.elementary.Seq;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterableOf;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class JoinedTest
{
    @Test
    public void testTrivial() throws Exception
    {
        assertThat(new Joined<>(EmptyIterable.instance()), emptyIterableOf(String.class));
        assertThat(new Joined<>(new Seq<>(EmptyIterable.instance())), emptyIterableOf(String.class));
    }


    @Test
    public void testSingle() throws Exception
    {
        assertThat(new Joined<>(new Seq<>(new SingletonIterable<>("1"))), contains("1"));
    }


    @Test
    public void testMultiSingle() throws Exception
    {
        assertThat(new Joined<>(new Seq<>(new SingletonIterable<>("1"), new SingletonIterable<>("2"), new SingletonIterable<>("3"))),
                contains("1", "2", "3"));
    }


    @Test
    public void testMulti() throws Exception
    {
        assertThat(new Joined<>(new Seq<>(new Seq<>("1", "2", "3"))), contains("1", "2", "3"));
        assertThat(new Joined<>(new Seq<>(new Seq<>("1", "2", "3"), EmptyIterable.instance())), contains("1", "2", "3"));
        assertThat(new Joined<>(new Seq<>(EmptyIterable.instance(), new Seq<>("1", "2", "3"), EmptyIterable.instance())),
                contains("1", "2", "3"));
    }


    @Test
    public void testMultiMulti() throws Exception
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
                                EmptyIterable.instance(),
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
                                EmptyIterable.instance()
                        )),
                contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));
    }


    @Test
    public void testVarArgCtor() throws Exception
    {
        assertThat(new Joined<>(
                        new Seq<>("1", "2", "3"),
                        new Seq<>("a", "b", "c"),
                        new Seq<>("z", "zz", "zzz")
                ),
                contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(new Joined<>(
                        EmptyIterable.instance(),
                        new Seq<>("1", "2", "3"),
                        new Seq<>("a", "b", "c"),
                        new Seq<>("z", "zz", "zzz")
                ),
                contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));

        assertThat(new Joined<>(
                        new Seq<>("1", "2", "3"),
                        new Seq<>("a", "b", "c"),
                        new Seq<>("z", "zz", "zzz"),
                        EmptyIterable.instance()
                ),
                contains("1", "2", "3", "a", "b", "c", "z", "zz", "zzz"));
    }
}