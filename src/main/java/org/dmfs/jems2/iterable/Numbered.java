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
import org.dmfs.jems2.generatable.Sequence;


/**
 * An {@link Iterable} decorator which pairs every value of another iterator with it's sequential ordinal number.
 * <p>
 * <h3>Example</h3>
 * <pre>
 * Numbered(["a", "b", "c"]) -&gt;
 * [
 *   (1, "a"),
 *   (2, "b"),
 *   (3, "c")
 * ]
 * </pre>
 */
public final class Numbered<T> extends DelegatingIterable<Pair<Integer, T>>
{
    public Numbered(Iterable<T> delegate)
    {
        this(delegate, 0, 1);
    }


    public Numbered(Iterable<T> delegate, int start)
    {
        this(delegate, start, 1);
    }


    public Numbered(Iterable<T> delegate, int start, int step)
    {
        super(new Paired<>(new Infinite<>(new Sequence<>(start, last -> last + step)), delegate));
    }
}
