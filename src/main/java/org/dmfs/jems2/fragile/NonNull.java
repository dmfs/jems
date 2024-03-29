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

package org.dmfs.jems2.fragile;

import org.dmfs.jems2.Fragile;
import org.dmfs.jems2.Generator;


/**
 * A {@link Fragile} that fails when the given value was {@code null}.
 */
public final class NonNull<T, E extends Exception> implements Fragile<T, E>
{
    private final T mValue;
    private final Generator<? extends E> mExceptionGenerator;


    public NonNull(T value, Generator<? extends E> exceptionGenerator)
    {
        mValue = value;
        mExceptionGenerator = exceptionGenerator;
    }


    @Override
    public T value() throws E
    {
        if (mValue == null)
        {
            throw mExceptionGenerator.next();
        }
        return mValue;
    }
}
