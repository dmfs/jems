/*
 * Copyright 2018 dmfs GmbH
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

package org.dmfs.jems.fluent.predicate;

import org.dmfs.jems.fluent.FluentPredicate;
import org.dmfs.jems.predicate.Predicate;
import org.dmfs.jems.predicate.composite.AllOf;
import org.dmfs.jems.predicate.composite.AnyOf;
import org.dmfs.jems.predicate.composite.NoneOf;
import org.dmfs.jems.predicate.composite.Not;
import org.dmfs.jems.predicate.composite.SingleWith;
import org.dmfs.jems.single.Single;


/**
 * A fluent {@link Predicate}.
 * <p>
 * Note, in most cases it's probably easier and better readable to use a lambda expression.
 *
 * @author Marten Gajda
 */
public final class Fluent<T> implements FluentPredicate<T>
{
    private final Predicate<T> mDelegate;


    public Fluent(Predicate<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public FluentPredicate<T> and(Predicate<T> predicate)
    {
        return new Fluent<>(new AllOf<>(mDelegate, predicate));
    }


    @Override
    public FluentPredicate<T> andAllOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new AllOf<>(mDelegate, new AllOf<>(predicates)));
    }


    @Override
    public FluentPredicate<T> andAnyOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new AllOf<>(mDelegate, new AnyOf<>(predicates)));
    }


    @Override
    public FluentPredicate<T> andNoneOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new AllOf<>(mDelegate, new NoneOf<>(predicates)));
    }


    @Override
    public FluentPredicate<T> or(Predicate<T> predicate)
    {
        return new Fluent<>(new AnyOf<>(mDelegate, predicate));
    }


    @Override
    public FluentPredicate<T> orAllOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new AnyOf<>(mDelegate, new AllOf<>(predicates)));
    }


    @Override
    public FluentPredicate<T> orAnyOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new AnyOf<>(mDelegate, new AnyOf<>(predicates)));
    }


    @Override
    public FluentPredicate<T> orNoneOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new AnyOf<>(mDelegate, new NoneOf<>(predicates)));
    }


    @Override
    public FluentPredicate<T> nor(Predicate<T> predicate)
    {
        return new Fluent<>(new Not<>(new AnyOf<>(mDelegate, predicate)));
    }


    @Override
    public FluentPredicate<T> norAllOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new Not<>(new AnyOf<>(mDelegate, new AllOf<>(predicates))));
    }


    @Override
    public FluentPredicate<T> norAnyOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new Not<>(new AnyOf<>(mDelegate, new AnyOf<>(predicates))));
    }


    @Override
    public FluentPredicate<T> norNoneOf(Iterable<Predicate<T>> predicates)
    {
        return new Fluent<>(new Not<>(new AnyOf<>(mDelegate, new NoneOf<>(predicates))));
    }


    @Override
    public FluentPredicate<T> unless(Predicate<T> predicate)
    {
        return new Fluent<>(new AllOf<>(mDelegate, new Not<>(predicate)));
    }


    @Override
    public FluentPredicate<T> unlessAllOf(Iterable<Predicate<T>> predicate)
    {
        return new Fluent<>(new AllOf<>(mDelegate, new Not<>(new AllOf<>(predicate))));
    }


    @Override
    public FluentPredicate<T> unlessAnyOf(Iterable<Predicate<T>> predicate)
    {
        return new Fluent<>(new AllOf<>(mDelegate, new Not<>(new AnyOf<>(predicate))));
    }


    @Override
    public FluentPredicate<T> unlessNoneOf(Iterable<Predicate<T>> predicate)
    {
        return new Fluent<>(new AllOf<>(mDelegate, new AnyOf<>(predicate)));
    }


    @Override
    public FluentPredicate<Single<T>> singleValue()
    {
        return new Fluent<>(new SingleWith<>(mDelegate));
    }


    @Override
    public boolean satisfiedBy(T testedInstance)
    {
        return mDelegate.satisfiedBy(testedInstance);
    }
}
