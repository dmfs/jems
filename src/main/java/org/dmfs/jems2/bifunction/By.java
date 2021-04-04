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

package org.dmfs.jems2.bifunction;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Diff;

import java.util.Comparator;


/**
 * A {@link BiFunction} which compares two instances of different types by mapping them onto a common type and using a {@link Comparator} to compare those
 * results.
 * <p>
 * This is particularly useful with {@link Diff}.
 *
 * @see org.dmfs.jems2.comparator.By
 */
public final class By<Left, Right> extends DelegatingBiFunction<Left, Right, Integer>
{

    public <V extends Comparable<? super V>> By(
        Function<? super Left, ? extends V> leftMappingFunction,
        Function<? super Right, ? extends V> rightMappingFunction)
    {
        this(leftMappingFunction, rightMappingFunction, Comparable::compareTo);
    }


    public <V> By(
        Function<? super Left, ? extends V> leftMappingFunction,
        Function<? super Right, ? extends V> rightMappingFunction,
        Comparator<? super V> delegate)
    {
        super((l, r) -> (delegate.compare(leftMappingFunction.value(l), rightMappingFunction.value(r))));
    }
}