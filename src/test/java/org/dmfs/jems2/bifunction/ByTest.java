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

package org.dmfs.jems2.bifunction;

import org.dmfs.jems2.comparator.Reverse;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


/**
 * Test {@link By}.
 */
public class ByTest
{
    @Test
    public void test()
    {
        assertThat(
            new By<>(String::length, String::length).value("123", "abcdef"),
            is(lessThan(0))
        );

        assertThat(
            new By<>(String::length, String::length, new Reverse<>(Integer::compareTo)).value("12345", "abcde"),
            is(equalTo(0))
        );

        assertThat(
            new By<>(String::length, String::length, Integer::compareTo).value("12345", "abc"),
            is(greaterThan(0))
        );
    }
}