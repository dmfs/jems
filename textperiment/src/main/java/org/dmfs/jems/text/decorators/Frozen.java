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

import org.dmfs.jems.single.Single;
import org.dmfs.jems.text.Text;

import java.util.Arrays;


/**
 * @author Marten Gajda
 */
public final class Frozen implements Text
{
    private final Single<char[]> mDelegate;


    public Frozen(final Text delegate)
    {
        mDelegate = new org.dmfs.jems.single.elementary.Frozen<>(new Single<char[]>()
        {
            @Override
            public char[] value()
            {
                return delegate.value();
            }
        });
    }


    @Override
    public char[] value()
    {
        // note, we still need to return a copy, to make sure the Frozen char[] is not altered
        char[] delegate = mDelegate.value();
        return Arrays.copyOf(delegate, delegate.length);
    }
}
