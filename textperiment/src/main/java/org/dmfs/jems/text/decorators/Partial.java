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

package org.dmfs.jems.text.decorators;

import org.dmfs.jems.text.Text;

import java.util.Arrays;


/**
 * @author Marten Gajda
 */
public final class Partial implements Text
{
    private final int mStart;
    private final int mEnd;
    private final Text mDelegate;


    public Partial(int start, Text delegate)
    {
        this(start, -1, delegate);
    }


    public Partial(int start, int end, Text delegate)
    {
        mStart = start;
        mEnd = end;
        mDelegate = delegate;
    }


    @Override
    public char[] value()
    {
        char[] delegate = mDelegate.value();
        int len = delegate.length;
        int start = mStart < 0 ? len + mStart + 1 : mStart;
        int end = mEnd < 0 ? len + mEnd + 1 : mEnd;
        if (start == 0 && end == len)
        {
            return delegate;
        }
        return Arrays.copyOfRange(delegate, start, end);
    }
}
