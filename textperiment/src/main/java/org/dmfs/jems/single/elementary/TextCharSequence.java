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
import org.dmfs.jems.text.TextSequence;

import java.util.Arrays;


/**
 * A lightweight version of {@link TextString} which returns "only" a {@link TextSequence}.
 *
 * @author Marten Gajda
 */
public final class TextCharSequence implements Single<TextSequence>
{
    private final Text mDelegate;


    public TextCharSequence(Text delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public TextSequence value()
    {
        return new CharArraySequence(mDelegate.value());
    }


    private static class CharArraySequence implements TextSequence
    {
        private final char[] mData;


        public CharArraySequence(char[] data)
        {
            mData = data;
        }


        @Override
        public int length()
        {
            return mData.length;
        }


        @Override
        public char charAt(int i)
        {
            return mData[i];
        }


        @Override
        public CharSequence subSequence(int i, int i1)
        {
            if (i == 0 && i1 == mData.length)
            {
                return this;
            }
            return new CharArraySequence(Arrays.copyOfRange(mData, i, i1));
        }


        @Override
        public String toString()
        {
            return new String(mData);
        }


        @Override
        public char[] value()
        {
            return Arrays.copyOf(mData, mData.length);
        }
    }
}
