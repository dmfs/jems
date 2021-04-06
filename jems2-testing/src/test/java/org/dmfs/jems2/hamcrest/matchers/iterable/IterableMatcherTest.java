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

package org.dmfs.jems2.hamcrest.matchers.iterable;

import org.dmfs.jems2.iterable.Seq;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.matches;
import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.mismatches;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class IterableMatcherTest
{
    @Test
    public void test()
    {
        assertThat(iteratesTo(1, 2, 3),
            allOf(
                matches(asList(1, 2, 3)),
                mismatches(asList(1, 2)),
                mismatches(asList(1, 2, 2)),
                mismatches(asList(1, 2, 3, 4))));

        assertThat(iteratesTo(new Seq<>(is(1), is(2), is(3))),
            allOf(
                matches(asList(1, 2, 3)),
                mismatches(asList(1, 2)),
                mismatches(asList(1, 2, 2)),
                mismatches(asList(1, 2, 3, 4))));

        assertThat(iteratesTo(is(1), is(2), is(3)),
            allOf(
                matches(asList(1, 2, 3)),
                mismatches(asList(1, 2)),
                mismatches(asList(1, 2, 2)),
                mismatches(asList(1, 2, 3, 4))));
    }
}