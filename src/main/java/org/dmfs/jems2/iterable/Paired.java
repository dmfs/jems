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

import org.dmfs.jems2.Pair;

import static org.dmfs.jems2.bifunction.PairingFunction.pairingFunction;


/**
 * An {@link Iterable} combining the elements of two given {@link Iterable}s into an {@link Iterable} of {@link Pair}s.
 * <p>
 * This iterates as many elements as the shorter of both {@link Iterable}s.
 * <h3>Example</h3>
 * <pre>
 * Paired(["a", "b", "c"], ["u", "v", "w", "x"]) -&gt;
 * [
 *   ("a", "u"),
 *   ("b", "v"),
 *   ("c", "w")
 * ]
 * </pre>
 */
public final class Paired<Left, Right> extends DelegatingIterable<Pair<Left, Right>>
{
    public Paired(Iterable<Left> leftIterable, Iterable<Right> rightIterable)
    {
        super(new Zipped<>(leftIterable, rightIterable, pairingFunction()));
    }

}
