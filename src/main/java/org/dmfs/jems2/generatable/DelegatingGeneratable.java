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

import org.dmfs.jems2.Generatable;
import org.dmfs.jems2.Generator;


/**
 * An abstract {@link Generatable} which delegates to another {@link Generatable}.
 */
public abstract class DelegatingGeneratable<T> implements Generatable<T>
{
    private final Generatable<T> mDelegate;


    public DelegatingGeneratable(Generatable<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final Generator<T> generator()
    {
        return mDelegate.generator();
    }
}
