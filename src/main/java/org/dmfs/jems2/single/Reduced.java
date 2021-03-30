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

package org.dmfs.jems2.single;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Generator;
import org.dmfs.jems2.Single;


/**
 * The {@link Single} value of a reduced {@link Iterable}.
 * <p>
 * This is basically the single threaded version of a "reduce" operation.
 */
public final class Reduced<Value, Result> implements Single<Result>
{
    private final Generator<? extends Result> mInitialValue;
    private final Iterable<? extends Value> mIterable;
    private final BiFunction<? super Result, ? super Value, ? extends Result> mFunction;


    public Reduced(Generator<? extends Result> initialValueGenerator,
        BiFunction<? super Result, ? super Value, ? extends Result> accumulatorFunction,
        Iterable<Value> iterable)
    {
        mInitialValue = initialValueGenerator;
        mIterable = iterable;
        mFunction = accumulatorFunction;
    }


    @Override
    public Result value()
    {
        Result result = mInitialValue.next();
        for (Value value : mIterable)
        {
            result = mFunction.value(result, value);
        }
        return result;
    }
}
