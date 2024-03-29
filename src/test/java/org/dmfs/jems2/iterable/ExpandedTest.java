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

import org.dmfs.jems2.Function;
import org.dmfs.jems2.optional.Present;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.mockito.doubles.TestDoubles.dummy;
import static org.dmfs.jems2.optional.Absent.absent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;


/**
 *
 */
public class ExpandedTest
{

    @Test
    public void testIterable()
    {
        Function<Object, Iterable<Object>> dummyFunction = dummy(Function.class);
        assertThat(new Expanded<>(dummyFunction, EmptyIterable.emptyIterable()), Matchers.emptyIterable());
        assertThat(new Expanded<>(
                s -> new Seq<>(s + "1", s + "2", s + "3"),
                new Seq<>("a", "b", "c")
            ),
            contains("a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"));
    }


    @Test
    public void testOptional()
    {
        Function<Object, Iterable<Object>> dummyFunction = dummy(Function.class);
        assertThat(new Expanded<>(dummyFunction, absent()), Matchers.emptyIterable());
        assertThat(new Expanded<>(
                Seq::new,
                new Present<>(new String[] { "a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3" })
            ),
            contains("a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"));
    }
}