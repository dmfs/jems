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
import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Pair;

import java.util.Iterator;


/**
 * An {@link Iterable} of the differences of two given {@link Iterable}s.
 * <p>
 * Note, to get correct results, the {@link Iterable}s must be sorted by the comparison criterion.
 *
 * <h2>Example</h2>
 * <pre>{@code
 * Diff([1,3,5,7], [3,4,5], Integer::compareTo) ->
 * [
 *   (1, -),
 *   (3, 3),
 *   (-, 4),
 *   (5, 5),
 *   (7, -)
 * ]
 * }</pre>
 */
public final class Diff<Left, Right> implements Iterable<Pair<Optional<Left>, Optional<Right>>>
{
    private final Iterable<Left> mLefts;
    private final Iterable<Right> mRights;
    private final BiFunction<? super Left, ? super Right, Integer> mComparatorFunction;


    public Diff(
        Iterable<Left> lefts,
        Iterable<Right> rights,
        BiFunction<? super Left, ? super Right, Integer> comparatorFunction)
    {
        mLefts = lefts;
        mRights = rights;
        mComparatorFunction = comparatorFunction;
    }


    @Override
    public Iterator<Pair<Optional<Left>, Optional<Right>>> iterator()
    {
        return new org.dmfs.jems2.iterator.Diff<>(mLefts.iterator(), mRights.iterator(), mComparatorFunction);
    }

}
