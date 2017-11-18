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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author marten
 */
public class Base64TextTest
{
    @Test
    public void testValue() throws Exception
    {
        assertThat(new Base64Text("".getBytes("UTF-8")).value(),
                is("".toCharArray()));
        assertThat(new Base64Text("1".getBytes("UTF-8")).value(),
                is("MQ==".toCharArray()));
        assertThat(new Base64Text("12".getBytes("UTF-8")).value(),
                is("MTI=".toCharArray()));
        assertThat(new Base64Text("123".getBytes("UTF-8")).value(),
                is("MTIz".toCharArray()));
        assertThat(new Base64Text("Polyfon zwitschernd aßen Mäxchens Vögel Rüben, Joghurt und Quark".getBytes("UTF-8")).value(),
                is("UG9seWZvbiB6d2l0c2NoZXJuZCBhw59lbiBNw6R4Y2hlbnMgVsO2Z2VsIFLDvGJlbiwgSm9naHVydCB1bmQgUXVhcms=".toCharArray()));
    }

}