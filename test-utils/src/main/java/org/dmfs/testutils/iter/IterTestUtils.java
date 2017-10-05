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

package org.dmfs.testutils.iter;

import org.dmfs.iterables.ArrayIterable;
import org.dmfs.iterators.ArrayIterator;

import java.util.Iterator;


/**
 * Convenience util methods for {@link Iterable}s and {@link Iterator}s.
 *
 * @author Gabor Keszthelyi
 */
public final class IterTestUtils
{

    /**
     * Creates a new {@link Iterable} with the given elements.
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Iterable<T> asIterable(T... elements)
    {
        return new ArrayIterable<>(elements);
    }


    /**
     * Creates a new {@link Iterator} with the given elements.
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Iterator<T> asIterator(T... items)
    {
        return new ArrayIterator<>(items);
    }
}
