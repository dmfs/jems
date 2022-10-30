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

package org.dmfs.jems2.hamcrest.matchers.single;

import org.dmfs.jems2.single.Just;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.*;
import static org.dmfs.jems2.hamcrest.matchers.single.SingleMatcher.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;


/**
 * Unit test for {@link SingleMatcher}.
 */
public class SingleMatcherTest
{

    @Test
    public void test()
    {
        assertThat(hasValue(new IsEqual<>("a")),
            allOf(
                matches(new Just<>("a")),
                mismatches(new Just<>("b"), "value was \"b\""),
                describesAs("value \"a\"")));

        assertThat(hasValue("a"),
            allOf(
                matches(new Just<>("a")),
                mismatches(new Just<>("b"), "value was \"b\""),
                describesAs("value \"a\"")));
    }
}