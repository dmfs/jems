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

package org.dmfs.jems2.iterator;

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.emptyIterator;
import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.iteratorOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ConcatTest
{
    @Test
    public void test()
    {
        assertThat(() -> new Concat<>(),
            is(emptyIterator()));

        assertThat(() -> new Concat<>(new Seq<>(1, 2, 3)),
            is(iteratorOf(1, 2, 3)));

        assertThat(() -> new Concat<>(new Seq<>(1, 2, 3), new Seq<>(4, 5, 6)),
            is(iteratorOf(1, 2, 3, 4, 5, 6)));

        assertThat(() -> new Concat<>(EmptyIterator.emptyIterator(), new Seq<>(1, 2, 3), EmptyIterator.emptyIterator(), new Seq<>(4, 5, 6)),
            is(iteratorOf(1, 2, 3, 4, 5, 6)));
    }
}