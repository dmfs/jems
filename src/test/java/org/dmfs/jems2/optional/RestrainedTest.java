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

package org.dmfs.jems2.optional;

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.optional.AbsentMatcher.absent;
import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Unit test for {@link Restrained}.
 */
public final class RestrainedTest
{
    @Test
    public void test()
    {
        assertThat(new Restrained<>(() -> true, new Absent<>()), is(absent()));
        assertThat(new Restrained<>(() -> false, new Absent<>()), is(absent()));
        assertThat(new Restrained<>(() -> true, new Present<>("abc")), is(present("abc")));
        assertThat(new Restrained<>(() -> false, new Present<>("abc")), is(absent()));
    }
}