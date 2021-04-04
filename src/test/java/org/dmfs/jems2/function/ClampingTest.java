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

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.function.FragileFunctionMatcher.associates;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Unit test for {@link Clamping}
 */
public final class ClampingTest
{
    @Test
    public void test()
    {
        assertThat(new Clamping<>(0, 1), associates(-1, is(0)));
        assertThat(new Clamping<>(0, 1), associates(0, is(0)));
        assertThat(new Clamping<>(0f, 1f), associates(0.5f, is(0.5f)));
        assertThat(new Clamping<>(0f, 1f), associates(1f, is(1f)));
        assertThat(new Clamping<>(0f, 1f), associates(2f, is(1f)));

        assertThat(new Clamping<>("b", "d"), associates("a", is("b")));
        assertThat(new Clamping<>("b", "d"), associates("e", is("d")));
    }

}