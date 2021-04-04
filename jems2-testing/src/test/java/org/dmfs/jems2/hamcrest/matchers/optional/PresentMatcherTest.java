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

package org.dmfs.jems2.hamcrest.matchers.optional;

import org.dmfs.jems2.optional.Present;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.dmfs.jems2.optional.Absent.absent;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.object.HasToString.hasToString;
import static org.junit.Assert.assertThat;


/**
 * Test for {@link PresentMatcher}.
 */
public class PresentMatcherTest
{
    @Test
    public void testMatchesSafely()
    {
        assertThat(new PresentMatcher<>().matchesSafely(absent(), new Description.NullDescription()), is(false));
        assertThat(new PresentMatcher<String>().matchesSafely(new Present<>("test"), new Description.NullDescription()), is(true));
        assertThat(new PresentMatcher<>("test").matchesSafely(new Present<>("test"), new Description.NullDescription()), is(true));
        assertThat(new PresentMatcher<>("test").matchesSafely(new Present<>("tost"), new Description.NullDescription()), is(false));

        assertThat(present().matchesSafely(new Present<>("test"), new Description.NullDescription()), is(true));
        assertThat(present("test").matchesSafely(new Present<>("test"), new Description.NullDescription()), is(true));
        assertThat(present(is("test")).matchesSafely(new Present<>("test"), new Description.NullDescription()), is(true));

        assertThat(present("tost").matchesSafely(new Present<>("test"), new Description.NullDescription()), is(false));
        assertThat(present(is("tost")).matchesSafely(new Present<>("test"), new Description.NullDescription()), is(false));
    }


    @Test
    public void testValueMismatchDescription()
    {
        Description mismatchMsg = new StringDescription();
        new PresentMatcher<>("123").describeMismatch(new Present<>("abc"), mismatchMsg);
        assertThat(mismatchMsg.toString(), is("present, but value was \"abc\""));
    }


    @Test
    public void testPresenceMismatchDescription()
    {
        Description mismatchMsg = new StringDescription();
        new PresentMatcher<>("123").describeMismatch(absent(), mismatchMsg);
        assertThat(mismatchMsg.toString(), is("not present"));
    }


    @Test
    public void testDescribeTo()
    {
        Description description = new StringDescription();
        new PresentMatcher<>("123").describeTo(description);
        assertThat(description, hasToString("present with value \"123\""));
    }

}