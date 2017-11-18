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

package org.dmfs.jems.text.decorators;

import org.dmfs.jems.text.elementary.EmptyText;
import org.dmfs.jems.text.elementary.StringText;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author marten
 */
public class TrimmedTest
{
    @Test
    public void testChars() throws Exception
    {
        assertThat(new Trimmed(new EmptyText()).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText(" ")).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText("  ")).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText("   ")).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText("\t")).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText(" \t ")).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText("\t  ")).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText("\t \t")).value(), is(new char[0]));
        assertThat(new Trimmed(new StringText("123")).value(), is("123".toCharArray()));

        assertThat(new Trimmed(new StringText(" 123")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("123 ")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText(" 123 ")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("   123")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("123   ")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("   123   ")).value(), is("123".toCharArray()));

        assertThat(new Trimmed(new StringText("1   2   3")).value(), is("1   2   3".toCharArray()));

        assertThat(new Trimmed(new StringText(" 1   2   3")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("1   2   3 ")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText(" 1   2   3 ")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("   1   2   3")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("1   2   3   ")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("   1   2   3   ")).value(), is("1   2   3".toCharArray()));

        assertThat(new Trimmed(new StringText("\t123")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("123\t")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("\t123\t")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("\t\t\t123")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("123\t\t\t")).value(), is("123".toCharArray()));
        assertThat(new Trimmed(new StringText("\t\t\t123\t\t\t")).value(), is("123".toCharArray()));

        assertThat(new Trimmed(new StringText("1   2   3")).value(), is("1   2   3".toCharArray()));

        assertThat(new Trimmed(new StringText("\t1   2   3")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("1   2   3\t")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("\t1   2   3\t")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("\t\t\t1   2   3")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("1   2   3\t\t\t")).value(), is("1   2   3".toCharArray()));
        assertThat(new Trimmed(new StringText("\t\t\t1   2   3\t\t\t")).value(), is("1   2   3".toCharArray()));

    }

}