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
public final class AsciiText implements Text
{
    private final Single<byte[]> mEncoded;


    public AsciiText(byte[] encoded)
    {
        this(new ValueSingle<>(encoded));
    }


    public AsciiText(Single<byte[]> encoded)
    {
        mEncoded = encoded;
    }


    @Override
    public char[] value()
    {
        byte[] data = mEncoded.value();
        int len = data.length;
        char[] result = new char[len];
        for (int i = 0; i < len; ++i)
        {
            byte value = data[i];
            if (value < 0)
            {
                throw new IllegalArgumentException(String.format("Invalid ASCII encoding %d at position %d", value, i));
            }
            result[i] = (char) value;
        }
        return result;
    }
}
