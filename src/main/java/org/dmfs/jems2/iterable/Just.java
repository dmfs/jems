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

package org.dmfs.jems2.iterable;

import org.dmfs.jems2.Single;
import org.dmfs.jems2.iterator.SingleIterator;

import java.util.Iterator;


/**
 * An {@link Iterable} which iterates a single value.
 */
public final class Just<T> implements Iterable<T>
{
    private final Single<T> mSingle;


    public Just(T value)
    {
        this(() -> value);
    }


    public Just(Single<T> single)
    {
        mSingle = single;
    }


    @Override
    public Iterator<T> iterator()
    {
        return new SingleIterator<>(mSingle);
    }
}
