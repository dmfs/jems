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

import java.util.Locale;


/**
 * The hexadecimal {@link CharSequence} representation of a byte array. The digits {@code a-f} will be represented by lower case letters.
 * <p>
 * Example:
 * <pre>
 * assertEquals(
 *     new Hex(new byte[{0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef }).toString(),
 *     "0123456789abcdef");
 * </pre>
 */
public final class Hex implements CharSequence
{
    private final static char[] NIBBLES = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private final byte[] mBinaryData;

    /**
     * Start in CharSequence coordinates.
     */
    private final int mStart;

    /**
     * End in CharSequence coordinates.
     */
    private final int mEnd;


    public Hex(byte[] binaryData)
    {
        this(0, binaryData.length * 2, binaryData);
    }


    public Hex(byte[] binaryData, int start, int end)
    {
        // convert array coordinates to CharSequence coordinates, each byte is represented as 2 chars
        this(start * 2, end * 2, binaryData);
    }


    private Hex(int start, int end, byte[] binaryData)
    {
        mBinaryData = binaryData;
        mStart = start;
        mEnd = end;
    }


    @Override
    public int length()
    {
        return mEnd - mStart;
    }


    @Override
    public char charAt(int index)
    {
        if (index < 0 || index >= mEnd - mStart)
        {
            throw new ArrayIndexOutOfBoundsException(
                String.format(Locale.ENGLISH, "Invalid index %d. Expected value between 0 and %d", index, mEnd - mStart));
        }
        return hex(index + mStart);
    }


    @Override
    public CharSequence subSequence(int start, int end)
    {
        if (start < 0 || end < start || end > mEnd - mStart)
        {
            throw new ArrayIndexOutOfBoundsException("invalid range");
        }
        return new Hex(start + mStart, end + mStart, mBinaryData);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(mBinaryData.length * 2);
        for (int i = mStart; i < mEnd; ++i)
        {
            sb.append(hex(i));
        }
        return sb.toString();
    }


    private char hex(int index)
    {
        return NIBBLES[(mBinaryData[index >> 1] >>> (4 - (index & 1) * 4)) & 0x0f];
    }
}
