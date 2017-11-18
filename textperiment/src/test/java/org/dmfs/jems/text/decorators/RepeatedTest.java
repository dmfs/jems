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
 * @author Marten Gajda
 */
public class RepeatedTest
{
    @Test
    public void testChars() throws Exception
    {
        assertThat(new Repeated(0, new EmptyText()).value(), is(new char[0]));
        assertThat(new Repeated(1, new EmptyText()).value(), is(new char[0]));
        assertThat(new Repeated(10, new EmptyText()).value(), is(new char[0]));

        assertThat(new Repeated(0, new StringText("1")).value(), is(new char[0]));
        assertThat(new Repeated(1, new StringText("1")).value(), is("1".toCharArray()));
        assertThat(new Repeated(10, new StringText("1")).value(), is("1111111111".toCharArray()));

        assertThat(new Repeated(0, new StringText("123")).value(), is(new char[0]));
        assertThat(new Repeated(1, new StringText("123")).value(), is("123".toCharArray()));
        assertThat(new Repeated(10, new StringText("123")).value(), is("123123123123123123123123123123".toCharArray()));
    }

}