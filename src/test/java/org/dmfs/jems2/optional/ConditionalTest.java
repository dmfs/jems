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

import org.dmfs.jems2.single.Just;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.optional.AbsentMatcher.absent;
import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Test for {@link Conditional}. O
 */
public final class ConditionalTest
{

    @Test
    public void test()
    {
        assertThat(new Conditional<>(v -> v == 1, 1), is(present(1)));
        assertThat(new Conditional<Integer>(v -> v == 1, new Just<>(1)), is(present(1)));

        assertThat(new Conditional<>(v -> v == 1, 2), is(absent()));
        assertThat(new Conditional<Integer>(v -> v == 1, new Just<>(2)), is(absent()));
    }

}