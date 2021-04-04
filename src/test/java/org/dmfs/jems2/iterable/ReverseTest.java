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

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ReverseTest
{
    @Test
    public void test()
    {
        assertThat(
            new Reverse<>(EmptyIterable.emptyIterable()),
            is(emptyIterable()));
        assertThat(
            new Reverse<>(new Seq<>(1)),
            iteratesTo(1));
        assertThat(
            new Reverse<>(new Seq<>(1, 2, 3)),
            iteratesTo(3, 2, 1));
    }

}