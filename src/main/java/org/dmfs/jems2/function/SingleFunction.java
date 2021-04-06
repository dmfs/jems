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

package org.dmfs.jems2.function;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Single;


/**
 * A {@link Function} which produces {@link Single}s of its arguments.
 */
public final class SingleFunction<T> implements Function<T, Single<T>>
{
    private static final Function<?, ?> INSTANCE = new SingleFunction<>();


    @SuppressWarnings("unchecked")
    public static <V> Function<V, Single<V>> toSingle()
    {
        return (Function<V, Single<V>>) INSTANCE;
    }


    @Override
    public Single<T> value(T t)
    {
        return () -> t;
    }
}
