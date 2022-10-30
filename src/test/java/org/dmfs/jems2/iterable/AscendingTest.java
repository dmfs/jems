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
 * Unit test for {@link Ascending}.
 */
public class AscendingTest
{
    @Test
    public void test()
    {
        assertThat(new Ascending<String>(new Seq<>()), is(emptyIterable()));
        assertThat(new Ascending<>(new Seq<>("z")), iteratesTo("z"));
        assertThat(new Ascending<>(new Seq<>("z", "x", "y")), iteratesTo("x", "y", "z"));
        assertThat(new Ascending<>(new Seq<>("z", "x", "y", "z", "x", "y")), iteratesTo("x", "x", "y", "y", "z", "z"));
    }
}