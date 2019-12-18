/*
 * Copyright 2019 dmfs GmbH
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

package org.dmfs.jems.procedure.composite;

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.iterables.SingletonIterable;
import org.dmfs.jems.iterable.elementary.Seq;
import org.dmfs.jems.optional.elementary.Absent;
import org.dmfs.jems.optional.elementary.Present;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.dmfs.jems.hamcrest.matchers.IterableMatcher.iteratesTo;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link ForEach}.
 *
 * @author Marten Gajda
 * <p>
 * // TODO can we write that using a Matcher?
 */
public class ForEachTest
{
    @Test
    public void testEmptyIterable()
    {
        List<String> args = new ArrayList<>();
        new ForEach<>(new EmptyIterable<String>()).process(args::add);
        assertThat(args, is(emptyIterable()));
    }


    @Test
    public void testSingletonIterable()
    {
        List<String> args = new ArrayList<>();
        new ForEach<>(new SingletonIterable<>("a")).process(args::add);
        assertThat(args, iteratesTo("a"));
    }


    @Test
    public void testIterable()
    {
        List<String> args = new ArrayList<>();
        new ForEach<>(new Seq<>("a", "b", "c")).process(args::add);
        assertThat(args, iteratesTo("a", "b", "c"));
    }


    @Test
    public void testAbsentOptional()
    {
        List<String> args = new ArrayList<>();
        new ForEach<>(new Absent<String>()).process(args::add);
        assertThat(args, is(emptyIterable()));
    }


    @Test
    public void testPresentOptional()
    {
        List<String> args = new ArrayList<>();
        new ForEach<>(new Present<>("a")).process(args::add);
        assertThat(args, iteratesTo("a"));
    }


    @Test
    public void testSingle()
    {
        List<String> args = new ArrayList<>();
        new ForEach<>(() -> "a").process(args::add);
        assertThat(args, iteratesTo("a"));
    }
}