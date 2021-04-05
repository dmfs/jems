/*
 * Copyright 2019 dmfs GmbH
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

package org.dmfs.jems.set.elementary;

import org.dmfs.jems.predicate.Predicate;
import org.dmfs.jems.set.Set;


/**
 * A {@link Set} of all elements which satisfy a given {@link Predicate}.
 *
 * @author Marten Gajda
 */
@Deprecated
public final class PredicateSet<T> implements Set<T>
{
    private final Predicate<? super T> mPredicate;


    public PredicateSet(Predicate<? super T> predicate)
    {
        mPredicate = predicate;
    }


    @Override
    public boolean contains(T element)
    {
        return mPredicate.satisfiedBy(element);
    }
}
