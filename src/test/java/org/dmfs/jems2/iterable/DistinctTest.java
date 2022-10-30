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

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.ParallelMatcher.parallel;
import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


/**
 * Unit test for {@link Distinct}.
 */
public class DistinctTest
{
    @Test
    public void test()
    {
        assertThat(new Distinct<>(new EmptyIterable<>()), is(emptyIterable()));
        assertThat(new Distinct<>(new Seq<>("1")), iteratesTo("1"));
        assertThat(new Distinct<>(new Seq<>("1", "2", "3")), iteratesTo("1", "2", "3"));

        assertThat(new Distinct<>(String::length, new EmptyIterable<>()), is(emptyIterable()));
        assertThat(new Distinct<>(String::length, new Seq<>("1")), iteratesTo("1"));
        assertThat(new Distinct<>(String::length, new Seq<>("1", "2", "3")), iteratesTo("1"));
        assertThat(new Distinct<>(String::length, new Seq<>("1", "11", "2", "22", "3", "33")), iteratesTo("1", "11"));
        assertThat(new Distinct<>(String::length, new Seq<>("33", "1", "11", "2", "22", "3")), iteratesTo("33", "1"));

        assertThat(new Distinct<>(String::length, new Seq<>("33", "1", "11", "2", "22", "3")), parallel(iteratesTo("33", "1")));
    }
}