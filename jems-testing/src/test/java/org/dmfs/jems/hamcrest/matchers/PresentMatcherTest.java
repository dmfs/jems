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

import org.dmfs.optional.Absent;
import org.dmfs.optional.Present;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.junit.Test;

import static org.junit.Assert.assertThat;


/**
 * Test for {@link PresentMatcher}.
 *
 * @author Marten Gajda
 */
public class PresentMatcherTest
{
    @Test
    public void testMatchesSafely() throws Exception
    {
        assertThat(new PresentMatcher<>().matchesSafely(Absent.absent(), new Description.NullDescription()), CoreMatchers.is(false));
        assertThat(new PresentMatcher<String>().matchesSafely(new Present<>("test"), new Description.NullDescription()), CoreMatchers.is(true));
        assertThat(new PresentMatcher<>("test").matchesSafely(new Present<>("test"), new Description.NullDescription()), CoreMatchers.is(true));
        assertThat(new PresentMatcher<>("test").matchesSafely(new Present<>("tost"), new Description.NullDescription()), CoreMatchers.is(false));
    }
}