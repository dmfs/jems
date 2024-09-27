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

package org.dmfs.jems2.fragileprocedure;

import org.dmfs.jems2.FragileProcedure;
import org.dmfs.jems2.iterable.Seq;


/**
 * A {@link FragileProcedure} composed of other procedures. Note, this {@link FragileProcedure} makes no attempt
 * at calling more delegates after the first one threw an {@link Exception}.
 * <h2>Example</h2>
 * <pre>{@code
 * new Composite<>(System.out::println, Logger::info).process("log message");
 * }</pre>
 * Prints "log message" to the console and to the log.
 */
public final class Composite<T, E extends Exception> implements FragileProcedure<T, E>
{
    private final Iterable<? extends FragileProcedure<? super T, ? extends E>> mDelegates;


    @SafeVarargs
    public Composite(FragileProcedure<? super T, ? extends E>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Composite(Iterable<? extends FragileProcedure<? super T, ? extends E>> delegates)
    {
        mDelegates = delegates;
    }


    @Override
    public void process(T arg) throws E
    {
        for (FragileProcedure<? super T, ? extends E> delegate : mDelegates)
        {
            delegate.process(arg);
        }
    }
}
