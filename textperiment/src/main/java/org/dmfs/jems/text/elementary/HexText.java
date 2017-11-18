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


/**
 * @author Marten Gajda
 */
public final class HexText implements Text
{
    private final static byte[] NIBBLES = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private final Single<byte[]> mBytes;


    public HexText(byte[] bytes)
    {
        this(new ValueSingle<>(bytes));
    }


    public HexText(Single<byte[]> bytes)
    {
        mBytes = bytes;
    }


    @Override
    public char[] value()
    {
        byte[] bytes = mBytes.value();
        int len = bytes.length;
        char[] result = new char[len * 2];
        int charPos = 0;
        int bytePos = 0;
        while (bytePos < len)
        {
            byte val = bytes[bytePos++];
            result[charPos++] = (char) NIBBLES[(val >>> 4) & 0x0f];
            result[charPos++] = (char) NIBBLES[val & 0x0f];
        }
        return result;
    }
}
