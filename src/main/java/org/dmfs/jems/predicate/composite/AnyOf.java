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

package org.dmfs.jems.predicate.composite;

import org.dmfs.jems.iterable.elementary.Seq;
import org.dmfs.jems.predicate.Predicate;


/**
 * A {@link Predicate} which is satisfied if any of a given number of predicates are satisfied. This is equivalent to the boolean "OR" operation.
 *
 * @author Marten Gajda
 */
public final class AnyOf<T> implements Predicate<T>
{
    private final Iterable<Predicate<T>> mDelegates;


    @SafeVarargs
    public AnyOf(Predicate<T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public AnyOf(Iterable<Predicate<T>> delegates)
    {
        mDelegates = delegates;
    }


    @Override
    public boolean satisfiedBy(T testedInstance)
    {
        boolean emtpy = true;
        for (Predicate<T> predicate : mDelegates)
        {
            if (predicate.satisfiedBy(testedInstance))
            {
                return true;
            }
            emtpy = false;
        }
        return emtpy;
    }
}
