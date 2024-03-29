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

package org.dmfs.jems2.single;

import org.dmfs.jems2.iterable.EmptyIterable;
import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.dmfs.jems2.hamcrest.matchers.single.SingleMatcher.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;


/**
 * Unit test for {@link Collected}.
 */
public class CollectedTest
{
    @Test
    public void test()
    {
        assertThat(
            new Collected<>(ArrayList::new, EmptyIterable.<String>emptyIterable()),
            hasValue(emptyIterable()));

        assertThat(
            new Collected<>(ArrayList::new, new Seq<>("a")),
            hasValue(iteratesTo("a")));

        assertThat(
            new Collected<>(ArrayList::new, new Seq<>("a", "b")),
            hasValue(iteratesTo("a", "b")));

        assertThat(
            new Collected<>(ArrayList::new, new Seq<>("a", "b", "c")),
            hasValue(iteratesTo("a", "b", "c")));

        assertThat(
            new Collected<>(ArrayList::new, new Seq<>("a", "a", "a")),
            hasValue(iteratesTo("a", "a", "a")));
    }
}