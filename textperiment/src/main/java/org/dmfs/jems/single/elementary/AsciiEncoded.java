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
import org.dmfs.jems.text.Text;


/**
 * @author Marten Gajda
 */
public final class AsciiEncoded implements Single<byte[]>
{
    private final Text mText;


    public AsciiEncoded(Text text)
    {
        mText = text;
    }


    @Override
    public byte[] value()
    {
        char[] charData = mText.value();
        int len = charData.length;
        byte[] byteData = new byte[len];
        for (int i = 0; i < len; i++)
        {
            char c = charData[i];
            if (c > 127)
            {
                throw new IllegalArgumentException(String.format("character %c at position %d not in ASCII range", c, i));
            }
            byteData[i] = (byte) c;
        }
        return byteData;
    }
}
