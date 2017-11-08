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

package org.dmfs.jems.charsequence.elementary;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class HexTest
{
    @Test
    public void testLength() throws Exception
    {
        assertThat(new Hex(new byte[0]).length(), is(0));
        assertThat(new Hex(new byte[] { 1 }).length(), is(2));
        assertThat(new Hex(new byte[] { 1, 2, 3 }).length(), is(6));

        assertThat(new Hex(new byte[] { 1 }, 0, 1).length(), is(2));
        assertThat(new Hex(new byte[] { 1, 2, 3 }, 1, 1).length(), is(0));
        assertThat(new Hex(new byte[] { 1, 2, 3 }, 1, 2).length(), is(2));
    }


    @Test
    public void testCharAt() throws Exception
    {
        assertThat(new Hex(new byte[] { 1 }).charAt(0), is('0'));
        assertThat(new Hex(new byte[] { 1 }).charAt(1), is('1'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(0), is('0'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(1), is('1'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(2), is('2'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(3), is('3'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(4), is('4'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(5), is('5'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(6), is('6'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(7), is('7'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(8), is('8'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(9), is('9'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(10), is('a'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(11), is('b'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(12), is('c'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(13), is('d'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(14), is('e'));
        assertThat(new Hex(new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).charAt(15), is('f'));

        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }, 1, 2).charAt(0), is('1'));
        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }, 1, 2).charAt(1), is('2'));
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCharAtFail1() throws Exception
    {
        new Hex(new byte[] {}).charAt(0);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCharAtFail2() throws Exception
    {
        new Hex(new byte[] { 0x01 }).charAt(-1);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCharAtFail3() throws Exception
    {
        new Hex(new byte[] { 0x01 }).charAt(2);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCharAtFail4() throws Exception
    {
        new Hex(new byte[] { 0x01, 0x02, 0x03 }, 1, 2).charAt(2);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCharAtFail5() throws Exception
    {
        new Hex(new byte[] { 0x01, 0x02, 0x03 }, 1, 2).charAt(-1);
    }


    @Test
    public void testSubSequence() throws Exception
    {
        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).subSequence(1, 5).toString(), is("f12a"));
        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).subSequence(1, 5).charAt(1), is('1'));
        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).subSequence(1, 5).length(), is(4));
        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).subSequence(1, 5).subSequence(1, 3).toString(), is("12"));

        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }, 1, 2).subSequence(1, 2).toString(), is("2"));
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubSequenceFail1() throws Exception
    {
        new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).subSequence(1, 7);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubSequenceFail2() throws Exception
    {
        new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).subSequence(-1, 5);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubSequenceFail3() throws Exception
    {
        new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).subSequence(4, 2);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubSequenceFail4() throws Exception
    {
        new Hex(new byte[] {}).subSequence(1, 1);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubSequenceFail5() throws Exception
    {
        new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }, 1, 2).subSequence(1, 3);
    }


    @Test
    public void testToString() throws Exception
    {
        assertThat(new Hex(new byte[] {}).toString(), is(""));
        assertThat(new Hex(new byte[] { 1 }).toString(), is("01"));
        assertThat(new Hex(new byte[] { (byte) 0xff, 0x12, (byte) 0xa9 }).toString(), is("ff12a9"));
    }
}
