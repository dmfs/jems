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

package org.dmfs.jems.text.elementary;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class AsciiTextTest
{
    @Test
    public void testChars() throws Exception
    {
        assertThat(new AsciiText(new byte[0]).value(), is(new char[0]));
        assertThat(new AsciiText(new byte[] { 'a' }).value(), is("a".toCharArray()));
        assertThat(new AsciiText(new byte[] { 'a', '1' }).value(), is("a1".toCharArray()));
        assertThat(new AsciiText(new byte[] { 'a', '1', ' ', '\t', '^' }).value(), is("a1 \t^".toCharArray()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testUnicodeChars() throws Exception
    {
        new AsciiText("ä".getBytes("UTF-8")).value();
    }
}