/*
 * Copyright 2018 dmfs GmbH
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

package org.dmfs.jems.generator.elementary;

import org.dmfs.jems.function.Function;
import org.dmfs.jems.generator.Generator;


/**
 * A sequence {@link Generator}. It generates a sequence by passing the previous result (or initial value) into a given {@link Function}.
 * <p>
 * Note, this {@link Generator} always generates the upcoming value ahead of time, which means it generates one more value than you actually need. For very
 * expensive functions you should consider using a different {@link Generator}.
 *
 * @author Marten Gajda
 */
public final class Sequence<T> implements Generator<T>
{
    private T mNext;
    private final Function<T, T> mFunction;


    public Sequence(T first, Function<T, T> function)
    {
        mNext = first;
        mFunction = function;
    }


    @Override
    public T next()
    {
        // TODO: is it ok to use try-finally like this or should we use the imperative version underneath?
        // I like this version because it looks very clean and expresses what I want to achieve (if you don't think of it as error handling).
        try
        {
            // return the next result
            return mNext;
        }
        finally
        {
            mNext = mFunction.value(mNext);
        }

        /*
        // imperative version
        T next = mNext;
        mNext = mFunction.value(next);
        return next;
        */
    }
}
