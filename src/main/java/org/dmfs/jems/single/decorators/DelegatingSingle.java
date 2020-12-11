/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.jems.single.decorators;

import org.dmfs.jems.fragile.Fragile;
import org.dmfs.jems.single.Single;


/**
 * A {@link Single} which delegates all calls to another {@link Single}.
 */
public abstract class DelegatingSingle<T> implements Single<T>
{
    private final Fragile<T, ? extends RuntimeException> mDelegate;


    protected DelegatingSingle(Fragile<T, ? extends RuntimeException> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final T value()
    {
        return mDelegate.value();
    }
}
