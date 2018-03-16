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

package org.dmfs.jems.fluent.iterable;

import org.dmfs.iterables.Distinct;
import org.dmfs.iterables.composite.PairZipped;
import org.dmfs.iterables.composite.Zipped;
import org.dmfs.iterables.decorators.Reverse;
import org.dmfs.iterables.decorators.Sieved;
import org.dmfs.jems.fluent.FluentIterable;
import org.dmfs.jems.fluent.FluentOptional;
import org.dmfs.jems.function.BiFunction;
import org.dmfs.jems.function.Function;
import org.dmfs.jems.iterable.composite.Joined;
import org.dmfs.jems.iterable.decorators.Mapped;
import org.dmfs.jems.iterable.decorators.Numbered;
import org.dmfs.jems.pair.Pair;
import org.dmfs.jems.predicate.Predicate;
import org.dmfs.optional.First;

import java.util.Iterator;


/**
 * @author Marten Gajda
 */
public final class Fluent<T> implements FluentIterable<T>
{
    private final Iterable<T> mDelegate;


    public Fluent(Iterable<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public <V> FluentIterable<V> mapped(Function<T, V> function)
    {
        return new Fluent<>(new Mapped<>(function, mDelegate));
    }


    @Override
    public FluentIterable<T> sieved(Predicate<T> predicate)
    {
        return new Fluent<>(new Sieved<>(predicate, mDelegate));
    }


    @Override
    public <V> FluentIterable<V> expanded(Function<T, Iterable<V>> expansionFunction)
    {
        return new Fluent<>(new Joined<>(new Mapped<>(expansionFunction, mDelegate)));
    }


    @Override
    public FluentIterable<T> joinedWith(Iterable<Iterable<T>> iterable)
    {
        return new Fluent<>(new Joined<>(mDelegate, new Joined<>(iterable)));
    }


    @Override
    public FluentIterable<T> joinedTo(Iterable<Iterable<T>> iterable)
    {
        return new Fluent<>(new Joined<>(new Joined<>(iterable), mDelegate));
    }


    @Override
    public FluentIterable<T> reversed()
    {
        return new Fluent<>(new Reverse<>(mDelegate));
    }


    @Override
    public FluentIterable<T> distinct()
    {
        return new Fluent<>(new Distinct<>(mDelegate));
    }


    @Override
    public <V, R> FluentIterable<R> zipped(Iterable<V> other, BiFunction<T, V, R> zipFunction)
    {
        return new Fluent<>(new Zipped<>(mDelegate, other, zipFunction));
    }


    @Override
    public <V> FluentIterable<Pair<T, V>> paired(Iterable<V> other)
    {
        return new Fluent<>(new PairZipped<>(mDelegate, other));
    }


    @Override
    public FluentIterable<Pair<Integer, T>> numbered()
    {
        return new Fluent<>(new Numbered<>(mDelegate));
    }


    @Override
    public FluentOptional<T> first()
    {
        return new org.dmfs.jems.fluent.optional.Fluent<>(new First<>(mDelegate));
    }


    @Override
    public FluentOptional<T> firstThat(Predicate<T> function)
    {
        return new org.dmfs.jems.fluent.optional.Fluent<>(new First<>(new Sieved<>(function, mDelegate)));
    }


    @Override
    public Iterator<T> iterator()
    {
        return mDelegate.iterator();
    }
}
