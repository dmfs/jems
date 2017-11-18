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

import java.nio.ByteBuffer;
import java.nio.charset.Charset;


/**
 * @author Marten Gajda
 */
public final class DecodedText implements Text
{
    private final Charset mCharset;
    private final Single<byte[]> mEncoded;


    public DecodedText(Charset charset, byte[] encoded)
    {
        this(charset, new ValueSingle<>(encoded));
    }


    public DecodedText(Charset charset, Single<byte[]> encoded)
    {
        mCharset = charset;
        mEncoded = encoded;
    }


    @Override
    public char[] value()
    {
        return mCharset.decode(ByteBuffer.wrap(mEncoded.value())).array();
    }
}
