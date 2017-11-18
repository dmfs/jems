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

import org.dmfs.jems.single.Single;
import org.dmfs.jems.single.elementary.ValueSingle;
import org.dmfs.jems.text.Text;

import java.util.Arrays;


/**
 * @author Marten Gajda
 */
public final class Utf8Text implements Text
{
    private final Single<byte[]> mEncoded;


    public Utf8Text(byte[] encoded)
    {
        this(new ValueSingle<>(encoded));
    }


    public Utf8Text(Single<byte[]> encoded)
    {
        mEncoded = encoded;
    }


    @Override
    public char[] value()
    {
        byte[] data = mEncoded.value();
        int len = data.length;

        // the input contains at most "len" characters
        char[] outBuffer = new char[len];

        int dataPos = 0;

        while (dataPos < len)
        {
            byte inData = data[dataPos];
            if (inData < 0)
            {
                // oops not an ASCII char, go to the slow lane
                break;
            }
            // still on the fast lane
            outBuffer[dataPos++] = (char) inData;
        }
        if (dataPos == len)
        {
            // hurray, all the value were ASCII value
            return outBuffer;
        }
        int outPos = dataPos;
        while (dataPos < len)
        {
            byte inData = data[dataPos++];
            if (inData < 0)
            {
                if ((inData & 0x0e0) == 0x0c0)
                {
                    // 2 byte code
                    outBuffer[outPos++] = (char) ((firstByte(inData, 0x1f) << 6) | nextByte(data[dataPos++]));
                }
                else if ((inData & 0x0f0) == 0x0e0)
                {
                    int codepoint = (firstByte(inData, 0x0f) << 12) | nextByte(data[dataPos++]) << 6 | nextByte(data[dataPos++]);
                    if (codepoint < 0x0ffff)
                    {
                        outBuffer[outPos++] = (char) codepoint;
                    }
                    else
                    {
                        codepoint -= 0x10000;
                        outBuffer[outPos++] = (char) (((codepoint >>> 10) & 0x03ff) | 0xD800);
                        outBuffer[outPos++] = (char) ((codepoint & 0x03ff) | 0x0DC00);
                    }
                }
                else if ((inData & 0x0f8) == 0x0f0)
                {
                    int codepoint = (firstByte(inData, 0x07) << 18) | nextByte(data[dataPos++]) << 12 | nextByte(data[dataPos++]) << 6 | nextByte(
                            data[dataPos++]);
                    codepoint -= 0x10000;
                    outBuffer[outPos++] = (char) (((codepoint >>> 10) & 0x03ff) | 0xD800);
                    outBuffer[outPos++] = (char) ((codepoint & 0x03ff) | 0x0DC00);
                }
                else
                {
                    throw new IllegalArgumentException("Illegal UTF-8 sequence");
                }
            }
            else
            {
                // ASCII range
                outBuffer[outPos++] = (char) inData;
            }
        }

        return Arrays.copyOfRange(outBuffer, 0, outPos);
    }


    private int firstByte(int b, int mask)
    {
        if ((b & ~mask) == 0)
        {
            throw new IllegalArgumentException("Illegal UTF-8 sequence");
        }
        return b & mask;
    }


    private int nextByte(int b)
    {
        if ((b & 0x0C0) != 0x080)
        {
            throw new IllegalArgumentException("Illegal UTF-8 sequence");
        }
        return b & 0x3f;
    }
}
