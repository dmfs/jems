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
 * {@link Function} to convert from a binary String ("0" or "1") to Boolean.
 *
 * @author Gabor Keszthelyi
 */
public final class BinaryStrToBoolFunc implements Function<String, Boolean>
{
    public static final Function<String, Boolean> INST = new BinaryStrToBoolFunc();


    private BinaryStrToBoolFunc()
    {
    }


    @Override
    public Boolean value(String argument)
    {
        if ("0".equals(argument))
        {
            return false;
        }
        if ("1".equals(argument))
        {
            return true;
        }
        throw new IllegalArgumentException("Binary string is not \"0\" or \"1\" but: " + argument);
    }
}
