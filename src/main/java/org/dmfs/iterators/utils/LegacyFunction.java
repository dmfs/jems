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

package org.dmfs.iterators.utils;

/**
 * Adapter from new to old version of Function interface for the transition period.
 *
 * @author Gabor Keszthelyi
 * @deprecated remove this adapter wherever possible and just use {@link org.dmfs.jems.function.Function}
 */
@Deprecated
public final class LegacyFunction<OriginalType, ResultType> implements org.dmfs.iterators.Function<OriginalType, ResultType>
{
    private final org.dmfs.jems.function.Function<OriginalType, ResultType> mFunction;


    public LegacyFunction(org.dmfs.jems.function.Function<OriginalType, ResultType> function)
    {
        mFunction = function;
    }


    @Override
    public ResultType apply(OriginalType argument)
    {
        return mFunction.value(argument);
    }
}
