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

package org.dmfs.jems.function.conversions;

import org.dmfs.jems.function.Function;


/**
 * {@link Function} to convert from String to Long.
 *
 * @author Gabor Keszthelyi
 */
public final class StringToLongFunc implements Function<String, Long>
{
    public static final Function<String, Long> INST = new StringToLongFunc();


    private StringToLongFunc()
    {
    }


    @Override
    public Long value(String argument)
    {
        return Long.valueOf(argument);
    }
}
