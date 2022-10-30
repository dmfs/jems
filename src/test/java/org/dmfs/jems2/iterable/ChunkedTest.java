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

import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


/**
 * Unit test for {@link Chunked}.
 */
public class ChunkedTest
{
    @Test
    public void test()
    {
        // error case, illegal chunk size
        assertThat(() -> new Chunked<>(-1, new Seq<>(1)), is(throwing(IllegalArgumentException.class)));
        assertThat(() -> new Chunked<>(0, new Seq<>(1)), is(throwing(IllegalArgumentException.class)));

        // edge case chunk size 1
        assertThat(new Chunked<>(1, new Seq<>()), is(emptyIterable()));
        assertThat(new Chunked<>(1, new Seq<>(1)), iteratesTo(iteratesTo(1)));
        assertThat(new Chunked<>(1, new Seq<>(1, 2, 3)), iteratesTo(iteratesTo(1), iteratesTo(2), iteratesTo(3)));

        // regular case chunk size >1
        assertThat(new Chunked<>(3, new Seq<>()), is(emptyIterable()));
        assertThat(new Chunked<>(3, new Seq<>(1)), iteratesTo(iteratesTo(1)));
        assertThat(new Chunked<>(3, new Seq<>(1, 2, 3)), iteratesTo(iteratesTo(1, 2, 3)));
        assertThat(new Chunked<>(3, new Seq<>(1, 2, 3, 4)), iteratesTo(iteratesTo(1, 2, 3), iteratesTo(4)));
        assertThat(new Chunked<>(3, new Seq<>(1, 2, 3, 4, 5, 6)), iteratesTo(iteratesTo(1, 2, 3), iteratesTo(4, 5, 6)));
        assertThat(new Chunked<>(3, new Seq<>(1, 2, 3, 4, 5, 6, 7)), iteratesTo(iteratesTo(1, 2, 3), iteratesTo(4, 5, 6), iteratesTo(7)));
    }
}