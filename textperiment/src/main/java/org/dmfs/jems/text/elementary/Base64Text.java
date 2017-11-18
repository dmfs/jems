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
import org.dmfs.jems.single.elementary.Base64Bytes;
import org.dmfs.jems.single.elementary.ValueSingle;


/**
 * @author Marten Gajda
 */
public final class Base64Text extends DelegatingText
{
    public Base64Text(byte[] bytes)
    {
        this(new ValueSingle<>(bytes));
    }


    public Base64Text(Single<byte[]> bytes)
    {
        super(new AsciiText(new Base64Bytes(bytes)));
    }
}
