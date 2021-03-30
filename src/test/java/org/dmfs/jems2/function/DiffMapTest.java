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

package org.dmfs.jems2.function;

import org.dmfs.jems2.optional.Absent;
import org.dmfs.jems2.optional.Present;
import org.dmfs.jems2.pair.ValuePair;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.LambdaMatcher.having;
import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.dmfs.jems2.hamcrest.matchers.function.FragileFunctionMatcher.associates;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link DiffMap}.
 */
public class DiffMapTest
{
    @Test
    public void test()
    {

        assertThat(new DiffMap<>((l, r) -> "lr" + l + r, (l) -> "l" + l, (r) -> "r" + r),
            having(d -> () -> d.value(new ValuePair<>(new Absent<>(), new Absent<>())),
                is(throwing(IllegalArgumentException.class))));

        assertThat(new DiffMap<>((l, r) -> "lr" + l + r, (l) -> "l" + l, (r) -> "r" + r),
            associates(new ValuePair<>(new Present<>("a"), new Present<>("b")),
                is("lrab")));

        assertThat(new DiffMap<>((l, r) -> "lr" + l + r, (l) -> "l" + l, (r) -> "r" + r),
            associates(new ValuePair<>(new Present<>("a"), new Absent<>()),
                is("la")));

        assertThat(new DiffMap<>((l, r) -> "lr" + l + r, (l) -> "l" + l, (r) -> "r" + r),
            associates(new ValuePair<>(new Absent<>(), new Present<>("b")),
                is("rb")));
    }
}