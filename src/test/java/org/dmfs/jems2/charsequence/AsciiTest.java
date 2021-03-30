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

package org.dmfs.jems2.charsequence;

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.charsequence.CharSequenceMatcher.validCharSequence;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Test {@link Ascii}.
 */
public class AsciiTest
{
    @Test
    public void test()
    {
        assertThat(new Ascii(), is(validCharSequence("")));
        assertThat(new Ascii(new byte[] { 'a' }), is(validCharSequence("a")));
        assertThat(new Ascii(new byte[] { 'a', 'b' }), is(validCharSequence("ab")));
        assertThat(new Ascii(new byte[] { 'a', 'b', 'c', 'd', 'e' }), is(validCharSequence("abcde")));
        assertThat(new Ascii(new byte[] { 'a', 'b' }, 0, 0), is(validCharSequence("")));
        assertThat(new Ascii(new byte[] { 'a', 'b' }, 1, 1), is(validCharSequence("")));
        assertThat(new Ascii(new byte[] { 'a', 'b' }, 0, 2), is(validCharSequence("ab")));
        assertThat(new Ascii(new byte[] { 'a', 'b', 'c', 'd', 'e' }, 2, 5), is(validCharSequence("cde")));
    }
}