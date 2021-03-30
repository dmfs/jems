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

package org.dmfs.jems2.generator;

import org.dmfs.jems2.Generator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Test {@link Sequence}.
 */
public final class SequenceTest
{
    @Test
    public void test()
    {
        Generator<Integer> seq = new Sequence<>(10, i -> i + 10);
        assertThat(seq.next(), is(10));
        assertThat(seq.next(), is(20));
        assertThat(seq.next(), is(30));
        assertThat(seq.next(), is(40));
        assertThat(seq.next(), is(50));
        assertThat(seq.next(), is(60));
        assertThat(seq.next(), is(70));
    }
}