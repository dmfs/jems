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

package org.dmfs.jems2.comparator;

import org.dmfs.jems2.optional.Absent;
import org.dmfs.jems2.optional.Present;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class OptionalComparatorTest
{
    @Test
    public void testPresent()
    {
        assertThat(new OptionalComparator<Integer>(Comparator.naturalOrder()).compare(
            new Present<>(1),
            new Present<>(2)),
            is(lessThan(0)));

        assertThat(new OptionalComparator<Integer>(Comparator.naturalOrder()).compare(
            new Present<>(2),
            new Present<>(2)),
            is(0));

        assertThat(new OptionalComparator<Integer>(Comparator.naturalOrder()).compare(
            new Present<>(4),
            new Present<>(2)),
            is(greaterThan(0)));
    }

    @Test
    public void testWithAbsent()
    {
        assertThat(new OptionalComparator<Integer>(Comparator.naturalOrder()).compare(
            new Absent<>(),
            new Present<>(2)),
            is(lessThan(0)));

        assertThat(new OptionalComparator<Integer>(Comparator.naturalOrder()).compare(
            new Absent<>(),
            new Absent<>()),
            is(0));

        assertThat(new OptionalComparator<Integer>(Comparator.naturalOrder()).compare(
            new Present<>(2),
            new Absent<>()),
            is(greaterThan(0)));
    }
}