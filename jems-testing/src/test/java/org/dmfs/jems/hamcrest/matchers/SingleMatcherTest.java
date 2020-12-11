/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.jems.hamcrest.matchers;

import org.dmfs.jems.single.elementary.ValueSingle;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.dmfs.jems.hamcrest.matchers.SingleMatcher.hasValue;
import static org.dmfs.jems.hamcrest.matchers.matcher.MatcherMatcher.describesAs;
import static org.dmfs.jems.hamcrest.matchers.matcher.MatcherMatcher.matches;
import static org.dmfs.jems.hamcrest.matchers.matcher.MatcherMatcher.mismatches;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertThat;


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
                matches(new ValueSingle<>("a")),
                mismatches(new ValueSingle<>("b"), "value was \"b\""),
                describesAs("value \"a\"")));

        assertThat(hasValue("a"),
            allOf(
                matches(new ValueSingle<>("a")),
                mismatches(new ValueSingle<>("b"), "value was \"b\""),
                describesAs("value \"a\"")));
    }
}