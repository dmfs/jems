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

import org.dmfs.jems2.optional.Present;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.dmfs.jems2.optional.Absent.absent;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link PresentValues}.
 */
public class PresentValuesTest
{
    @Test
    public void test()
    {
        assertThat(new PresentValues<>(absent()), is(emptyIterable()));
        assertThat(new PresentValues<>(absent(), absent(), absent()), is(emptyIterable()));
        assertThat(new PresentValues<>(new EmptyIterable<>()), is(emptyIterable()));
        assertThat(new PresentValues<>(new Seq<>(absent())), is(emptyIterable()));
        assertThat(new PresentValues<>(new Seq<>(absent(), absent(), absent())), is(emptyIterable()));

        assertThat(
            new PresentValues<>(new Present<>("abc")),
            iteratesTo("abc"));
        assertThat(
            new PresentValues<>(
                absent(),
                absent(),
                new Present<>("abc"),
                absent(),
                absent()),
            iteratesTo("abc"));
        assertThat(
            new PresentValues<>(
                new Present<>("abc"),
                new Present<>("xyz"),
                new Present<>("123")),
            iteratesTo("abc", "xyz", "123"));
        assertThat(
            new PresentValues<>(
                absent(),
                new Present<>("abc"),
                absent(),
                new Present<>("xyz"),
                absent(),
                new Present<>("123"),
                absent()),
            iteratesTo("abc", "xyz", "123"));

        assertThat(
            new PresentValues<>(
                new Seq<>(new Present<>("abc"))),
            iteratesTo("abc"));
        assertThat(
            new PresentValues<>(
                new Seq<>(
                    absent(),
                    absent(),
                    new Present<>("abc"),
                    absent(),
                    absent())),
            iteratesTo("abc"));
        assertThat(
            new PresentValues<>(
                new Seq<>(
                    new Present<>("abc"),
                    new Present<>("xyz"),
                    new Present<>("123"))),
            iteratesTo("abc", "xyz", "123"));
        assertThat(
            new PresentValues<>(
                new Seq<>(
                    absent(),
                    new Present<>("abc"),
                    absent(),
                    new Present<>("xyz"),
                    absent(),
                    new Present<>("123"),
                    absent())),
            iteratesTo("abc", "xyz", "123"));
    }
}