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

package org.dmfs.jems.text.composite;

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.iterables.elementary.Seq;
import org.dmfs.jems.text.Text;
import org.dmfs.jems.text.elementary.EmptyText;
import org.dmfs.jems.text.elementary.StringText;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class JoinedTest
{
    @Test
    public void testChars() throws Exception
    {
        assertThat(new Joined(EmptyText.INSTANCE, EmptyIterable.<Text>instance()).value(), is(new char[0]));
        assertThat(new Joined(new StringText(":"), EmptyIterable.<Text>instance()).value(), is(new char[0]));
        assertThat(new Joined(new StringText(":"), new Seq<Text>(new StringText("1"))).value(), is("1".toCharArray()));
        assertThat(new Joined(new StringText(":"), new Seq<Text>(new StringText("1"), new StringText("2"))).value(), is("1:2".toCharArray()));
        assertThat(new Joined(new StringText(":::"), new Seq<Text>(new StringText("1"), new StringText("2"))).value(), is("1:::2".toCharArray()));
        assertThat(new Joined(new StringText(":"), new Seq<Text>(new StringText("1ab"), new StringText("2"))).value(), is("1ab:2".toCharArray()));
        assertThat(new Joined(new StringText(":"), new Seq<Text>(new StringText("1ab"), new StringText("2xy"))).value(), is("1ab:2xy".toCharArray()));
        assertThat(new Joined(new StringText(":::"), new Seq<Text>(new StringText("1ab"), new StringText("2xy"))).value(), is("1ab:::2xy".toCharArray()));
        assertThat(new Joined(new StringText(":::"), new Seq<Text>(new StringText("1ab"), new StringText("2xy"), new StringText("3"))).value(),
                is("1ab:::2xy:::3".toCharArray()));
        assertThat(new Joined(new StringText(":::"), new Seq<Text>(new StringText("1ab"), new StringText("2xy"), new StringText("3qwer"))).value(),
                is("1ab:::2xy:::3qwer".toCharArray()));
    }

}