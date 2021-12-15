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

package org.dmfs.jems2.comparator;

import org.dmfs.jems2.Optional;

import java.util.Comparator;


/**
 * A {@link Comparator} for {@link Optional} values. This is primarily meant to be used with {@link OptionalComparator} if
 * an absent value is supposed to be larger than a present value.
 */
public final class GreaterAbsent<V> extends DelegatingComparator<Optional<? extends V>>
{
    public GreaterAbsent(Comparator<? super Optional<? extends V>> delegate)
    {
        super((l, r) -> l.isPresent() != r.isPresent()
            ? l.isPresent() ? -1 : 1
            : delegate.compare(l, r));
    }
}
