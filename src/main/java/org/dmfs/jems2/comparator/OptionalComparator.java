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
import org.dmfs.jems2.optional.FirstPresent;
import org.dmfs.jems2.optional.Mapped;
import org.dmfs.jems2.optional.Zipped;
import org.dmfs.jems2.single.Backed;

import java.util.Comparator;


/**
 * A {@link Comparator} for {@link Optional} values.
 * <p>
 * Absent values are always "smaller" than present values. Decorate this with {@link GreaterAbsent} to reverse this behavior.
 */
public final class OptionalComparator<V> implements Comparator<Optional<? extends V>>
{
    private final Comparator<? super V> mDelegate;


    public OptionalComparator(Comparator<? super V> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public int compare(Optional<? extends V> o1, Optional<? extends V> o2)
    {
        return new Backed<Integer>(
            new Zipped<>(o1, o2, mDelegate::compare),
            new Backed<>(
                new FirstPresent<>(new Mapped<>(i -> 1, o1), new Mapped<>(i -> -1, o2)), 0))
            .value();
    }
}
