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

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 *
 */
public class HexTest
{
    @Test
    public void testValue()
    {
        assertThat(new Hex(new Just<>(new byte[0])).value().toString(), is(""));
        assertThat(new Hex(new Just<>(new byte[] { 0x01 })).value().toString(), is("01"));
        assertThat(new Hex(new Just<>(new byte[] { 0x01, 0x02 })).value().toString(), is("0102"));
    }

}