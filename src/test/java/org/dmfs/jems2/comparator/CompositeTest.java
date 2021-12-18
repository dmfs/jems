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

import org.dmfs.jems2.iterable.Seq;
import org.junit.Test;

import static java.util.Comparator.naturalOrder;
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableEqualsMatcher.considersEqual;
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableOrderMatcher.imposesOrderOf;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertThat;


public class CompositeTest
{
    @Test
    public void testEmpty()
    {
        assertThat(new Composite<>(), considersEqual(1, "", new Object(), false));
    }


    @Test
    public void testSingle()
    {
        assertThat(new Composite<>(new By<>(String::length)),
            allOf(
                imposesOrderOf("", "1", "ab", "---"),
                considersEqual("  ", "ab", "12")
            ));

        assertThat(new Composite<>(new Seq<>(new By<>(String::length))),
            allOf(
                imposesOrderOf("", "1", "ab", "---"),
                considersEqual("  ", "ab", "12")
            ));
    }


    @Test
    public void testMultiple()
    {
        assertThat(new Composite<String>(new By<>(String::length), naturalOrder()),
            imposesOrderOf("", "1", "2", "x", "ab", "bc", "abc", "bcd"));

        assertThat(new Composite<String>(new Seq<>(new By<>(String::length), naturalOrder())),
            imposesOrderOf("", "1", "2", "x", "ab", "bc", "abc", "bcd"));
    }
}