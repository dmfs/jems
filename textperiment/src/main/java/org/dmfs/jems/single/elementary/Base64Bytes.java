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

import org.dmfs.jems.single.Single;


/**
 * @author Marten Gajda
 */
public final class Base64Bytes implements Single<byte[]>
{
    private final static byte[] DIGITS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };

    private final Single<byte[]> mBytes;


    public Base64Bytes(byte[] bytes)
    {
        this(new ValueSingle<>(bytes));
    }


    public Base64Bytes(Single<byte[]> bytes)
    {
        mBytes = bytes;
    }


    @Override
    public byte[] value()
    {
        byte[] source = mBytes.value();
        int sourceLen = source.length;
        int targetLen = ((sourceLen + 2) / 3) * 4;
        byte[] result = new byte[targetLen];
        int inPos = 2;
        int outPos = 0;
        for (; inPos < sourceLen; inPos += 3)
        {
            int word = (((int) source[inPos - 2]) << 16) | ((((int) source[inPos - 1]) & 0x0ff) << 8) | (((int) source[inPos])) & 0x0ff;
            result[outPos++] = DIGITS[(word >>> 18) & 0x3f];
            result[outPos++] = DIGITS[(word >>> 12) & 0x3f];
            result[outPos++] = DIGITS[(word >>> 6) & 0x3f];
            result[outPos++] = DIGITS[word & 0x3f];
        }
        if (inPos == sourceLen)
        {
            int word = (((int) source[inPos - 2]) << 16) + (((int) source[inPos - 1]) << 8);
            result[outPos++] = DIGITS[(word >>> 18) & 0x3f];
            result[outPos++] = DIGITS[(word >>> 12) & 0x3f];
            result[outPos++] = DIGITS[(word >>> 6) & 0x3f];
            result[outPos] = '=';
        }
        if (inPos == sourceLen + 1)
        {
            int word = (((int) source[inPos - 2]) << 16);
            result[outPos++] = DIGITS[(word >>> 18) & 0x3f];
            result[outPos++] = DIGITS[(word >>> 12) & 0x3f];
            result[outPos++] = '=';
            result[outPos] = '=';
        }

        return result;
    }
}
