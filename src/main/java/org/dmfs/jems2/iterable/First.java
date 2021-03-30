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

import org.dmfs.jems2.Generatable;


/**
 * An {@link Iterable} decorator which returns only the first few elements of the delegate.
 */
public final class First<T> extends DelegatingIterable<T>
{
    public First(Generatable<T> delegate)
    {
        this(1, delegate);
    }


    public First(Iterable<T> delegate)
    {
        this(1, delegate);
    }


    public First(int limit, Generatable<T> delegate)
    {
        super(() -> new org.dmfs.jems2.iterator.First<>(limit, delegate.generator()));
    }


    public First(int limit, Iterable<T> delegate)
    {
        super(() -> new org.dmfs.jems2.iterator.First<>(limit, delegate.iterator()));
    }
}
