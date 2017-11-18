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
import org.dmfs.jems.text.elementary.EmptyText;

import java.util.Arrays;


/**
 * @author Marten Gajda
 */
public final class Trimmed implements Text
{
    private final Text mDelegate;


    public Trimmed(Text delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public char[] value()
    {
        char[] delegate = mDelegate.value();
        int len = delegate.length;
        int start = 0;
        int end = len - 1;
        while (start < len && delegate[start] <= ' ')
        {
            start++;
        }
        while (end > start && delegate[end] <= ' ')
        {
            end--;
        }
        if (start == 0 && end == len - 1)
        {
            return delegate;
        }
        if (start == end)
        {
            return EmptyText.INSTANCE.value();
        }
        return Arrays.copyOfRange(delegate, start, end + 1);
    }
}
