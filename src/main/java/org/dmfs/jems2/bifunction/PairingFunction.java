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
import org.dmfs.jems2.Pair;
import org.dmfs.jems2.pair.ValuePair;


/**
 * A {@link BiFunction} that creates a {@link Pair} from the two arguments.
 */
public final class PairingFunction<Left, Right> implements BiFunction<Left, Right, Pair<Left, Right>>
{
    private final static BiFunction<?, ?, ?> INSTANCE = new PairingFunction<>();


    @SuppressWarnings("unchecked")
    public static <Left, Right> BiFunction<Left, Right, Pair<Left, Right>> pairingFunction()
    {
        return (BiFunction<Left, Right, Pair<Left, Right>>) INSTANCE;
    }


    @Override
    public Pair<Left, Right> value(Left left, Right right)
    {
        return new ValuePair<>(left, right);
    }
}
