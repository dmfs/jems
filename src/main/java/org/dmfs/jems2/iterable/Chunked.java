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

import java.util.Iterator;
import java.util.Locale;


/**
 * An {@link Iterable} decorator which returns the elements of the decorated {@link Iterable} in chunks of a specific size.
 *
 * <h2>Example</h2>
 * <pre>{@code
 * Chunked(3, [2, 4, 6, 8, 10, 12, 14, 16]) ->
 * [
 *   [2, 4, 6],
 *   [8, 10, 12],
 *   [14, 16]
 * ]
 * }</pre>
 */
public final class Chunked<T> implements Iterable<Iterable<T>>
{
    private final int mChunkSize;
    private final Iterable<T> mDelegate;


    public Chunked(int chunkSize, Iterable<T> delegate)
    {
        if (chunkSize <= 0)
        {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Chunk size must be >0 but was %s", chunkSize));
        }
        mChunkSize = chunkSize;
        mDelegate = delegate;
    }


    @Override
    public Iterator<Iterable<T>> iterator()
    {
        return new org.dmfs.jems2.iterator.Chunked<>(mChunkSize, mDelegate.iterator());
    }
}
