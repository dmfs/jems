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
 * @author marten
 */
public class Utf8TextTest
{
    @Test
    public void testChars() throws Exception
    {
        assertThat(new Utf8Text("".getBytes("UTF-8")).value(), is("".toCharArray()));
        assertThat(new Utf8Text("abcde".getBytes("UTF-8")).value(), is("abcde".toCharArray()));
        assertThat(new Utf8Text("abcdeäöü".getBytes("UTF-8")).value(), is("abcdeäöü".toCharArray()));
        assertThat(new Utf8Text("$".getBytes("UTF-8")).value(), is("$".toCharArray()));
        assertThat(new Utf8Text("¢".getBytes("UTF-8")).value(), is("¢".toCharArray()));
        assertThat(new Utf8Text("€".getBytes("UTF-8")).value(), is("€".toCharArray()));
        assertThat(new Utf8Text("\uD800\uDF48".getBytes("UTF-8")).value(), is("\uD800\uDF48".toCharArray()));
        assertThat(new Utf8Text("$¢€\uD800\uDF48".getBytes("UTF-8")).value(), is("$¢€\uD800\uDF48".toCharArray()));
        assertThat(new Utf8Text("\uD800\uDF48$¢€".getBytes("UTF-8")).value(), is("\uD800\uDF48$¢€".toCharArray()));
        assertThat(new Utf8Text("€\uD800\uDF48$¢".getBytes("UTF-8")).value(), is("€\uD800\uDF48$¢".toCharArray()));
        assertThat(new Utf8Text("€$¢\uD800\uDF48".getBytes("UTF-8")).value(), is("€$¢\uD800\uDF48".toCharArray()));
    }

}