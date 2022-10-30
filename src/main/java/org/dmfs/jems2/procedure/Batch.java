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

import org.dmfs.jems2.Procedure;


/**
 * A {@link Procedure}, which processes the elements of an {@link Iterable} using another {@link Procedure}.
 * <h2>Example</h2>
 * <pre>{@code
 * new Batch(System.out::println).process(new Seq<>("one", "two", "three"));
 * }</pre>
 * Calls the Function {@code System.out.println(String)} with each of the three {@code String}s.
 */
public final class Batch<T> implements Procedure<Iterable<? extends T>>
{
    private final Procedure<? super T> mDelegate;


    public Batch(Procedure<? super T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public void process(Iterable<? extends T> argument)
    {
        for (T value : argument)
        {
            mDelegate.process(value);
        }
    }
}
