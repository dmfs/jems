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

import org.dmfs.jems2.BiFunction;


/**
 * An {@link Iterable} combining the elements of two given {@link Iterable}s using a {@link BiFunction}.
 * <p>
 * This iterates as many elements as the shorter of both {@link Iterable}s.
 */
public final class Zipped<Result> extends DelegatingIterable<Result>
{
    public <Left, Right> Zipped(
        Iterable<Left> left,
        Iterable<Right> right,
        BiFunction<? super Left, ? super Right, ? extends Result> function)
    {
        super(() -> new org.dmfs.jems2.iterator.Zipped<>(left.iterator(), right.iterator(), function));
    }
}
