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

package org.dmfs.jems2.function;

import org.dmfs.jems2.FragileFunction;
import org.dmfs.jems2.Function;
import org.dmfs.jems2.ThrowingFunction;


/**
 * A {@link FragileFunction} to {@link Function} adapter which wraps any {@link Exception} into a {@link RuntimeException}.
 */
public final class Unchecked<Argument, Result, E extends Throwable> implements Function<Argument, Result>
{
    private final Function<? super E, ? extends RuntimeException> mExceptionFunction;
    private final ThrowingFunction<Argument, Result> mDelegate;


    public Unchecked(ThrowingFunction<Argument, Result> delegate)
    {
        this("Function call failed", delegate);
    }


    public Unchecked(
        String message,
        ThrowingFunction<Argument, Result> delegate)
    {
        this(exception -> new RuntimeException(message, exception), delegate);
    }


    public Unchecked(
        Function<? super E, ? extends RuntimeException> exceptionFunction,
        ThrowingFunction<Argument, Result> delegate)
    {
        mExceptionFunction = exceptionFunction;
        mDelegate = delegate;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Result value(Argument argument)
    {
        try
        {
            return mDelegate.value(argument);
        }
        catch (Throwable e)
        {
            throw mExceptionFunction.value((E) e);
        }
    }
}
