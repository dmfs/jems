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

package org.dmfs.jems2.fragile;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Fragile;
import org.dmfs.jems2.FragileBiFunction;


/**
 * A {@link Fragile} which combines two other {@link Fragile}s with a {@link FragileBiFunction}.
 */
public final class Zipped<Result, E extends Exception> extends DelegatingFragile<Result, E>
{
    public <Left, Right> Zipped(
        Fragile<Left, ? extends E> left,
        Fragile<Right, ? extends E> right,
        BiFunction<? super Left, ? super Right, ? extends Result> zipFunction)
    {
        this(left, right, (FragileBiFunction<? super Left, ? super Right, ? extends Result, ? extends E>) zipFunction::value);
    }


    public <Left, Right> Zipped(
        Fragile<Left, ? extends E> left,
        Fragile<Right, ? extends E> right,
        FragileBiFunction<? super Left, ? super Right, ? extends Result, ? extends E> zipFunction)
    {
        super(() -> zipFunction.value(left.value(), right.value()));
    }
}
