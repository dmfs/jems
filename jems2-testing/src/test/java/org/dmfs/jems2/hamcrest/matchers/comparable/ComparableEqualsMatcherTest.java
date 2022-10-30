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
import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableEqualsMatcher.considersEqual;
import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;


public class ComparableEqualsMatcherTest
{
    @Test
    public void test()
    {
        Integer v1 = new Integer(1);
        Integer v2 = new Integer(1);
        Integer v3 = new Integer(1);
        assertThat(considersEqual(v1, v2, v3),
            allOf(
                matches(naturalOrder()),
                // construct broken comparators
                mismatches((l, r) -> l.equals(v1) && r.equals(v2) ? 1 : 0, "<1> and <1> compared non-equal but to <1>"),
                mismatches((l, r) -> l.equals(v1) && r.equals(v3) ? -1 : 0, "<1> and <1> compared non-equal but to <-1>"),
                describesAs("considers the following elements equal: <1>,<1>,<1>")));
    }
}