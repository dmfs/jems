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

package org.dmfs.jems2.single;

import org.dmfs.jems2.optional.Absent;
import org.dmfs.jems2.optional.Present;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.dmfs.jems2.hamcrest.matchers.single.SingleMatcher.hasValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link Backed}.
 */
public final class BackedTest
{

    @Test
    public void test()
    {
        assertThat(new Backed<String>(new Present<>("test"), new Just<>("fallback")),
            hasValue("test"));
        assertThat(new Backed<>(new Present<>("test"), "fallback"),
            hasValue("test"));

        assertThat(new Backed<>(new Absent<String>(), new Just<>("fallback")),
            hasValue("fallback"));
        assertThat(new Backed<>(new Absent<String>(), "fallback"),
            hasValue("fallback"));

        assertThat(new Backed<String>(new Present<>("test"), new Just<>("fallback")),
            is(present("test")));
        assertThat(new Backed<>(new Present<>("test"), "fallback"),
            is(present("test")));

        assertThat(new Backed<>(new Absent<String>(), new Just<>("fallback")),
            is(present("fallback")));
        assertThat(new Backed<>(new Absent<String>(), "fallback"),
            is(present("fallback")));
    }

}