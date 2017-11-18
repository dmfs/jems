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

import java.util.Locale;


/**
 * @author Marten Gajda
 */
public final class Upper implements Text
{
    private final Text mDelegate;
    private final Locale mLocale;


    public Upper(Text delegate)
    {
        this(Locale.ENGLISH, delegate);
    }


    public Upper(Locale locale, Text delegate)
    {
        mLocale = locale;
        mDelegate = delegate;
    }


    @Override
    public char[] value()
    {
        // TODO: this is rather inefficient
        return new String(mDelegate.value()).toUpperCase(mLocale).toCharArray();
    }
}
