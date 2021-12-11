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

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Optional;


/**
 * A decorator for {@link Iterable} which expands each element into an {@link Iterable} (using the given function) and joins the results.
 * <p>
 * This resembles what other frameworks call "flatmap".
 */
public final class Expanded<T> extends DelegatingIterable<T>
{
    public <V> Expanded(Function<? super V, ? extends Iterable<? extends T>> function, Optional<V> delegate)
    {
        super(new Joined<>(new org.dmfs.jems2.optional.Mapped<>(function, delegate)));
    }


    public <V> Expanded(Function<? super V, ? extends Iterable<? extends T>> function, Iterable<V> delegate)
    {
        super(new Joined<>(new Mapped<>(function, delegate)));
    }
}
