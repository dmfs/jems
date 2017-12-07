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

package org.dmfs.jems.function.adapters;

import org.dmfs.jems.function.Function;


/**
 * Adapter from the old to the new version of Function.
 *
 * @author Gabor Keszthelyi
 * @deprecated use {@link Function} instead of {@link org.dmfs.iterators.Function} wherever possible
 */
@Deprecated
public final class NewFunction<Argument, Value> implements Function<Argument, Value>
{
    private final org.dmfs.iterators.Function<Argument, Value> mLegacyFunction;


    public NewFunction(org.dmfs.iterators.Function<Argument, Value> legacyFunction)
    {
        mLegacyFunction = legacyFunction;
    }


    @Override
    public Value value(Argument argument)
    {
        return mLegacyFunction.apply(argument);
    }
}
