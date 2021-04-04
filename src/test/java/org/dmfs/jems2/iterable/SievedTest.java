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

import org.dmfs.jems2.predicate.Anything;
import org.dmfs.jems2.predicate.Nothing;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


/**
 * Test {@link Sieved}.
 */
public class SievedTest
{
    @Test
    public void test()
    {
        assertThat(new Sieved<>(new Anything<>(), new EmptyIterable<>()),
            is(emptyIterable()));

        assertThat(new Sieved<>(new Anything<>(), new Seq<>(1, 2, 3)),
            iteratesTo(1, 2, 3));

        assertThat(new Sieved<>(new Nothing<>(), new EmptyIterable<>()),
            is(emptyIterable()));

        assertThat(new Sieved<>(new Nothing<>(), new Seq<>(1, 2, 3)),
            is(emptyIterable()));

        assertThat(new Sieved<>(i -> i % 2 == 0, new Seq<>(1, 2, 3, 4, 5, 6)),
            iteratesTo(2, 4, 6));
    }
}