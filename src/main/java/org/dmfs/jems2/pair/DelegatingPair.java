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

package org.dmfs.jems2.pair;

import org.dmfs.jems2.Pair;


/**
 * An abstract {@link Pair} which delegates to another {@link Pair}.
 */
public abstract class DelegatingPair<L, R> implements Pair<L, R>
{
    private final Pair<L, R> mDelegate;


    public DelegatingPair(Pair<L, R> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final L left()
    {
        return mDelegate.left();
    }


    @Override
    public final R right()
    {
        return mDelegate.right();
    }
}
