/*
 * Copyright 2021 dmfs GmbH
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

package org.dmfs.jems2.optional;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Optional;

import java.util.NoSuchElementException;


/**
 * {@link Optional} that maps an {@link Optional} if it's present using the given {@link Function}.
 */
public final class Mapped<From, To> implements Optional<To>
{
    private final Optional<From> mFromValue;
    private final Function<? super From, ? extends To> mConversion;


    public Mapped(Function<? super From, ? extends To> conversion, Optional<From> fromValue)
    {
        mConversion = conversion;
        mFromValue = fromValue;
    }


    @Override
    public boolean isPresent()
    {
        return mFromValue.isPresent();
    }


    @Override
    public To value() throws NoSuchElementException
    {
        return mConversion.value(mFromValue.value());
    }
}