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

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


/**
 * Test {@link Zipped}.
 */
public class ZippedTest
{
    @Test
    public void test()
    {
        assertThat(new Zipped<>(new EmptyIterable<>(), new EmptyIterable<>(), (l, r) -> l),
            is(emptyIterable()));

        assertThat(new Zipped<>(new Seq<>(1, 2, 3), new EmptyIterable<>(), (l, r) -> l),
            is(emptyIterable()));

        assertThat(new Zipped<>(new EmptyIterable<>(), new Seq<>(1, 2, 3), (l, r) -> r),
            is(emptyIterable()));

        assertThat(new Zipped<>(new Seq<>(10, 20, 30), new Seq<>(1, 2, 3), Integer::sum),
            iteratesTo(11, 22, 33));
    }
}