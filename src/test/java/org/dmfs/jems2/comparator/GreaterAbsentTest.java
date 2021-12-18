
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

import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableEqualsMatcher.considersEqual;
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableOrderMatcher.imposesOrderOf;
import static org.dmfs.jems2.optional.Absent.absent;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertThat;


public class GreaterAbsentTest
{
    @Test
    public void testOrder()
    {
        assertThat(new GreaterAbsent<>(new OptionalComparator<>(Comparator.naturalOrder())),
            imposesOrderOf(new Present<>(1), new Present<>(2), absent()));
    }


    @Test
    public void testEqual()
    {
        assertThat(new GreaterAbsent<>(new OptionalComparator<>(new By<>(String::length))),
            allOf(
                considersEqual(new Present<>("---"), new Present<>("123"), new Present<>("abc")),
                considersEqual(new Absent<>(), new Absent<>())));
    }
}