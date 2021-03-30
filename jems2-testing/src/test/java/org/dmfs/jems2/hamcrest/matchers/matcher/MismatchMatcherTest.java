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

package org.dmfs.jems2.hamcrest.matchers.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.*;
import static org.dmfs.jems2.mockito.doubles.TestDoubles.failingMock;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;


/**
 * Unit test for {@link MismatchMatcher}.
 */
public class MismatchMatcherTest
{

    @Test
    public void testMatches()
    {
        String dummy = "dummy";
        Matcher<String> mockMatcher = failingMock(Matcher.class);
        doReturn(false).when(mockMatcher).matches(dummy);
        doAnswer(invocation -> {
            Description description = invocation.getArgument(1);
            description.appendText("mismatch description");
            return null;
        }).when(mockMatcher).describeMismatch(same(dummy), any(Description.class));

        assertThat(new MismatchMatcher<>(dummy, is("mismatch description")), matches(mockMatcher));
        assertThat(mismatches(dummy), matches(mockMatcher));
        assertThat(mismatches(dummy, "mismatch description"), matches(mockMatcher));
        assertThat(mismatches(dummy, is("mismatch description")), matches(mockMatcher));
    }


    @Test
    public void testMismatches()
    {
        String dummy = "dummy";
        Matcher mockMatcher = failingMock(Matcher.class);
        doReturn(true).when(mockMatcher).matches(dummy);

        assertThat(new MismatchMatcher<>(dummy, is("mismatch description")).matches(mockMatcher), is(false));
        assertThat(mismatches(dummy).matches(mockMatcher), is(false));
        assertThat(mismatches(dummy, "mismatch description").matches(mockMatcher), is(false));
        assertThat(mismatches(dummy, is("mismatch description")).matches(mockMatcher), is(false));
    }


    @Test
    public void testMismatchDescription1()
    {
        String dummy = "dummy";
        Matcher mockMatcher = failingMock(Matcher.class);
        doReturn(true).when(mockMatcher).matches(dummy);

        Description description = new StringDescription();
        new MismatchMatcher<>(dummy, is("description")).describeMismatch(mockMatcher, description);

        assertThat(description.toString(), is("did match \"dummy\""));
    }


    @Test
    public void testMismatchDescription2()
    {
        String dummy = "dummy";
        Matcher mockMatcher = failingMock(Matcher.class);
        doReturn(false).when(mockMatcher).matches(dummy);
        doAnswer(invocation -> {
            Description description = invocation.getArgument(1);
            description.appendText("text");
            return null;
        }).when(mockMatcher).describeMismatch(same(dummy), any(Description.class));

        Description description = new StringDescription();
        new MismatchMatcher<>(dummy, is("description")).describeMismatch(mockMatcher, description);

        assertThat(description.toString(), is("description was \"text\""));
    }


    @Test
    public void testDescribeTo()
    {
        assertThat(new MismatchMatcher<>("test", is("description")), describesAs("mismatches with description is \"description\""));
    }
}