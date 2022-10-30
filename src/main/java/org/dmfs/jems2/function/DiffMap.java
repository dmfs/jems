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

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.Function;
import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Pair;
import org.dmfs.jems2.iterable.Diff;


/**
 * A {@link Function} to be used to map the result of {@link Diff} using three delegate functions.
 * <p>
 * <h2>Example</h2>
 *
 * <pre>{@code
 * new Mapped<>(
 *     new DiffMap<>(
 *         (left, right) -> …,
 *
 *         (left) -> …,
 *
 *         (right) -> …
 *     ),
 *     new Diff<>(lefts, rights, comparator));
 * }</pre>
 */
public final class DiffMap<Left, Right, Result> implements Function<Pair<? extends Optional<? extends Left>, ? extends Optional<? extends Right>>, Result>
{
    private final BiFunction<? super Left, ? super Right, ? extends Result> mLeftRightFunction;
    private final Function<? super Left, ? extends Result> mLeftFunction;
    private final Function<? super Right, ? extends Result> mRightFunction;


    public DiffMap(
        BiFunction<? super Left, ? super Right, ? extends Result> leftRightFunction,
        Function<? super Left, ? extends Result> leftFunction,
        Function<? super Right, ? extends Result> rightFunction)
    {
        mLeftRightFunction = leftRightFunction;
        mLeftFunction = leftFunction;
        mRightFunction = rightFunction;
    }


    @Override
    public Result value(Pair<? extends Optional<? extends Left>, ? extends Optional<? extends Right>> pair)
    {
        if (pair.left().isPresent() && pair.right().isPresent())
        {
            return mLeftRightFunction.value(pair.left().value(), pair.right().value());
        }
        else if (pair.left().isPresent())
        {
            return mLeftFunction.value(pair.left().value());
        }
        else if (pair.right().isPresent())
        {
            return mRightFunction.value(pair.right().value());
        }
        else
        {
            throw new IllegalArgumentException("Both, left and right were absent!");
        }
    }
}
