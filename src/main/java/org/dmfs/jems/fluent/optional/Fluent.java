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

package org.dmfs.jems.fluent.optional;

import org.dmfs.iterables.elementary.PresentValues;
import org.dmfs.jems.fluent.FluentIterable;
import org.dmfs.jems.fluent.FluentOptional;
import org.dmfs.jems.function.BiFunction;
import org.dmfs.jems.function.Function;
import org.dmfs.jems.optional.Optional;
import org.dmfs.jems.optional.composite.Zipped;
import org.dmfs.jems.optional.decorators.Frozen;
import org.dmfs.jems.optional.decorators.MapCollapsed;
import org.dmfs.jems.optional.decorators.Mapped;
import org.dmfs.jems.optional.decorators.Restrained;
import org.dmfs.jems.optional.decorators.Sieved;
import org.dmfs.jems.predicate.Predicate;
import org.dmfs.jems.single.Single;
import org.dmfs.jems.single.combined.Backed;

import java.util.NoSuchElementException;


/**
 * @author Marten Gajda
 */
public final class Fluent<T> implements FluentOptional<T>
{
    private final Optional<T> mDelegate;


    public Fluent(Optional<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public <V> FluentOptional<V> mapped(Function<T, V> function)
    {
        return new Fluent<>(new Mapped<>(function, mDelegate));
    }


    @Override
    public <V> FluentOptional<V> mapCollapsed(Function<T, Optional<V>> function)
    {
        return new Fluent<>(new MapCollapsed<>(function, mDelegate));
    }


    @Override
    public FluentOptional<T> sieved(Predicate<T> predicate)
    {
        return new Fluent<>(new Sieved<>(predicate, mDelegate));
    }


    @Override
    public FluentOptional<T> restrained(Single<Boolean> condition)
    {
        return new Fluent<>(new Restrained<>(condition, mDelegate));
    }


    @Override
    public <S, Result> FluentOptional<Result> zipped(Optional<S> right, BiFunction<T, S, Result> zipFunction)
    {
        return new Fluent<>(new Zipped<>(mDelegate, right, zipFunction));
    }


    @Override
    public FluentOptional<T> frozen()
    {
        return new Fluent<>(new Frozen<>(mDelegate));
    }


    @Override
    public Single<T> backedBy(Single<T> fallback)
    {
        return new Backed<>(mDelegate, fallback);
    }


    @Override
    public FluentIterable<T> presentIterable()
    {
        return new org.dmfs.jems.fluent.iterable.Fluent<>(new PresentValues<>(mDelegate));
    }


    @Override
    public boolean isPresent()
    {
        return mDelegate.isPresent();
    }


    @Override
    public T value() throws NoSuchElementException
    {
        return mDelegate.value();
    }
}
