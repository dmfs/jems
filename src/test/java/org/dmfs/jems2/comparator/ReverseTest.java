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

package org.dmfs.jems2.comparator;

import org.junit.Test;

import java.util.Comparator;

import static org.dmfs.jems2.hamcrest.matchers.comparable.ComparableOrderMatcher.imposesOrderOf;
import static org.junit.Assert.assertThat;


/**
 * Test {@link Reverse}.
 */
public class ReverseTest
{
    @Test
    public void test()
    {
        assertThat(new Reverse<>(Comparator.naturalOrder()),
            imposesOrderOf(5, 4, 3, 2, 1));
        assertThat(new Reverse<>(Comparator.reverseOrder()),
            imposesOrderOf(1, 2, 3, 4, 5));
    }
}