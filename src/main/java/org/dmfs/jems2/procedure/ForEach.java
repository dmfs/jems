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

package org.dmfs.jems2.procedure;

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.Single;
import org.dmfs.jems2.iterable.Just;
import org.dmfs.jems2.iterable.PresentValues;
import org.dmfs.jems2.iterable.Seq;


/**
 * The Object Oriented version of a for-each loop. It executes a given {@link Procedure} once for each of the given arguments.
 * <p>
 * This is somewhat orthogonal to the {@link Batch} {@link Procedure}.
 */
public final class ForEach<T> implements Procedure<Procedure<? super T>>
{
    private final Iterable<T> mArguments;


    public ForEach(Iterable<T> arguments)
    {
        mArguments = arguments;
    }


    public ForEach(Optional<T> argument)
    {
        this(new PresentValues<T>(argument));
    }


    public ForEach(Single<T> argument)
    {
        this(new Just<>(argument));
    }


    @SafeVarargs
    public ForEach(T... arguments)
    {
        this(new Seq<>(arguments));
    }


    @Override
    public void process(Procedure<? super T> arg)
    {
        for (T item : mArguments)
        {
            arg.process(item);
        }
    }
}
