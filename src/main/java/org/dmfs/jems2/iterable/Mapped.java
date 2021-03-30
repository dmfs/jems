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


/**
 * {@link Iterable} decorator maps the results of the delegate {@link Iterable} using a given {@link Function}.
 */
public final class Mapped<OriginalType, ResultType> extends DelegatingIterable<ResultType>
{
    public Mapped(Function<? super OriginalType, ? extends ResultType> mapFunction, Iterable<OriginalType> original)
    {
        super(() -> new org.dmfs.jems2.iterator.Mapped<>(mapFunction, original.iterator()));
    }
}
