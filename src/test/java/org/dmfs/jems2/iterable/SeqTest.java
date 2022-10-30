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
import static org.dmfs.jems2.hamcrest.matchers.throwable.ThrowableMatcher.throwable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;


/**
 * Test of {@link Seq}.
 */
public class SeqTest
{
    @Test
    public void testIterator()
    {
        assertThat(new Seq<>(), emptyIterable());
        assertThat(new Seq<>("1"), iteratesTo("1"));
        assertThat(new Seq<>("1", "2", "3"), iteratesTo("1", "2", "3"));

        assertThat(new Seq<>(0, new String[] {}), emptyIterable());
        assertThat(new Seq<>(0, new String[] { "1", "2", "3" }), emptyIterable());
        assertThat(new Seq<>(1, new String[] { "1" }), iteratesTo("1"));
        assertThat(new Seq<>(1, new String[] { "1", "2", "3" }), iteratesTo("1"));
        assertThat(new Seq<>(3, new String[] { "1", "2", "3" }), iteratesTo("1", "2", "3"));

        assertThat(() -> new Seq<>(1, new String[] {}), is(throwing(throwable(ArrayIndexOutOfBoundsException.class))));
        assertThat(() -> new Seq<>(4, new String[] { "1", "2", "3" }), is(throwing(throwable(ArrayIndexOutOfBoundsException.class))));
        assertThat(() -> new Seq<>(4, new String[] { "1" }), is(throwing(throwable(ArrayIndexOutOfBoundsException.class))));
        assertThat(() -> new Seq<>(-1, new String[] {}), is(throwing(throwable(ArrayIndexOutOfBoundsException.class))));
        assertThat(() -> new Seq<>(-1, new String[] { "1", "2", "3" }), is(throwing(throwable(ArrayIndexOutOfBoundsException.class))));
        assertThat(() -> new Seq<>(-2, new String[] { "1" }), is(throwing(throwable(ArrayIndexOutOfBoundsException.class))));
    }

}