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

package org.dmfs.jems2.optional;

import java.util.Iterator;


/**
 * The next value of an {@link Iterator}.
 * <p>
 * Note this is evaluated lazily, which means the value depends on the state of the given {@link Iterator}. Once retrieved, the value remains stable though.
 */
public final class Next<E> extends LazyDelegatingOptional<E>
{
    public Next(Iterator<E> iterator)
    {
        super(() -> iterator.hasNext() ? new Present<>(iterator.next()) : Absent.absent());
    }
}
