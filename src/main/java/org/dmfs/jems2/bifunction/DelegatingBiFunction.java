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

package org.dmfs.jems2.bifunction;

import org.dmfs.jems2.BiFunction;


/**
 * An abstract {@link BiFunction} which delegates to another given {@link BiFunction}.
 */
public abstract class DelegatingBiFunction<Left, Right, Result> implements BiFunction<Left, Right, Result>
{
    private final BiFunction<Left, Right, Result> mDelegate;


    protected DelegatingBiFunction(BiFunction<Left, Right, Result> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final Result value(Left left, Right right)
    {
        return mDelegate.value(left, right);
    }
}