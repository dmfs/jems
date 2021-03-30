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

import org.dmfs.jems2.Optional;


/**
 * {@link Iterable} which iterates over the present values in the input {@link Iterable} of {@link Optional}s.
 */
public final class PresentValues<T> extends DelegatingIterable<T>
{
    @SafeVarargs
    public PresentValues(Optional<? extends T>... optionals)
    {
        this(new Seq<>(optionals));
    }


    public PresentValues(Iterable<? extends Optional<? extends T>> optionals)
    {
        super(new Mapped<>(Optional::value, new Sieved<>(Optional::isPresent, optionals)));
    }
}
