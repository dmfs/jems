/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.iterables.composite;

import org.dmfs.iterables.decorators.DelegatingIterable;
import org.dmfs.jems.function.BiFunction;
import org.dmfs.jems.pair.Pair;
import org.dmfs.jems.pair.elementary.ValuePair;


/**
 * An {@link Iterable} combining the elements of two given {@link Iterable}s into an {@link Iterable} of {@link Pair}s.
 * <p>
 * This iterates as many elements as the shorter of both {@link Iterable}s.
 *
 * @author Gabor Keszthelyi
 */
public final class ZippedPairs<Left, Right> extends DelegatingIterable<Pair<Left, Right>>
{

    public ZippedPairs(Iterable<Left> left, Iterable<Right> right)
    {
        super(new Zipped<>(left, right, new PairingFunction<Left, Right>()));
    }


    private static final class PairingFunction<Left, Right> implements BiFunction<Left, Right, Pair<Left, Right>>
    {

        @Override
        public Pair<Left, Right> value(Left left, Right right)
        {
            return new ValuePair<>(left, right);
        }
    }
}
