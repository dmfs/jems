/*
 * Copyright 2019 dmfs GmbH
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

package org.dmfs.jems.procedure.composite;

import org.dmfs.jems.procedure.Procedure;


/**
 * A {@link Procedure}, which processes the elements of an {@link Iterable} using another {@link Procedure}.
 *
 * @author Marten Gajda
 */
public final class Batch<T> implements Procedure<Iterable<T>>
{
    private final Procedure<T> mDelegate;


    public Batch(Procedure<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public void process(Iterable<T> argument)
    {
        for (T value : argument)
        {
            mDelegate.process(value);
        }
    }
}