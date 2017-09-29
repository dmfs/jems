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

package org.dmfs.jems.single.elementary;

import org.dmfs.jems.single.Single;


/**
 * {@link Single} decorator that queries the delegate only once and returns the same value instance ever after.
 *
 * @author Gabor Keszthelyi
 */
public final class Frozen<T> implements Single<T>
{
    private final Single<T> mDelegate;

    private T mFrozenValue;


    public Frozen(Single<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public T value()
    {
        if (mFrozenValue == null)
        {
            mFrozenValue = mDelegate.value();
        }
        return mFrozenValue;
    }
}
