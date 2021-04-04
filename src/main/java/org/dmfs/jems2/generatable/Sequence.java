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

package org.dmfs.jems2.generatable;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Generatable;
import org.dmfs.jems2.Generator;


/**
 * A sequence {@link Generatable}. It generates a sequence by passing the previous result (or initial value) into a given {@link Function}.
 */
public final class Sequence<T> implements Generatable<T>
{
    private final Generator<T> mInitialValue;
    private final Function<? super T, ? extends T> mFunction;


    public Sequence(Generator<T> initialValue, Function<? super T, ? extends T> function)
    {
        mInitialValue = initialValue;
        mFunction = function;
    }


    public Sequence(T initialValue, Function<T, T> function)
    {
        this(() -> initialValue, function);
    }


    @Override
    public Generator<T> generator()
    {
        return new org.dmfs.jems2.generator.Sequence<>(mInitialValue.next(), mFunction);
    }
}
