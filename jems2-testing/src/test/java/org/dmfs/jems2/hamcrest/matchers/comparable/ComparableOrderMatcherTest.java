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

package org.dmfs.jems2.hamcrest.matchers.comparable;

import org.junit.jupiter.api.Test;

import static java.util.Comparator.naturalOrder;
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableOrderMatcher.imposesOrderOf;
import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;


public class ComparableOrderMatcherTest
{
    @Test
    public void test()
    {
        assertThat(imposesOrderOf(1, 2, 3, 4),
            allOf(
                matches(naturalOrder()),
                // construct broken comparators
                mismatches((l, r) -> l.equals(2) && r.equals(3) ? 1 : l - r, "compared elements <2> at index 1 and <3> at index 2  in the wrong order (1)"),
                mismatches((l, r) -> l.equals(3) && r.equals(2) ? -1 : l - r, "compared elements <3> at index 2 and <2> at index 1  in the wrong order (-1)"),
                mismatches((l, r) -> l.equals(2) && r.equals(2) ? 1 : l - r, "compared <2> at index 1 to be non equal to itself (1)"),
                describesAs("imposes the following element order <1>,<2>,<3>,<4>")));
    }
}