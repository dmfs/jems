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

package org.dmfs.jems.single.elementary;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class MessageDigestTest
{
    @Test
    public void value() throws Exception
    {
        assertThat(new MessageDigest("MD5").value().getAlgorithm(), is("MD5"));
        assertThat(new MessageDigest("SHA-1").value().getAlgorithm(), is("SHA-1"));
        assertThat(new MessageDigest("SHA-256").value().getAlgorithm(), is("SHA-256"));
    }


    @Test(expected = RuntimeException.class)
    public void valueInvalid() throws Exception
    {
        assertThat(new MessageDigest("BOGUS-FAKE-INVALID").value().getAlgorithm(), is("MD5"));
    }
}