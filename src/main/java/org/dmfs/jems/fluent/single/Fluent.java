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

package org.dmfs.jems.fluent.single;

import org.dmfs.iterables.elementary.SingleIterable;
import org.dmfs.jems.fluent.FluentIterable;
import org.dmfs.jems.fluent.FluentOptional;
import org.dmfs.jems.fluent.FluentSingle;
import org.dmfs.jems.optional.adapters.Conditional;
import org.dmfs.jems.optional.adapters.SinglePresent;
import org.dmfs.jems.predicate.Predicate;
import org.dmfs.jems.single.Single;
import org.dmfs.jems.single.elementary.Frozen;


/**
 * @author Marten Gajda
 */
public final class Fluent<T> implements FluentSingle<T>
{
    private final Single<T> mDelegate;


    public Fluent(Single<T> mDelegate)
    {
        this.mDelegate = mDelegate;
    }


    @Override
    public FluentSingle<T> frozen()
    {
        return new Fluent<>(new Frozen<>(mDelegate));
    }


    @Override
    public FluentOptional<T> present()
    {
        return new org.dmfs.jems.fluent.optional.Fluent<>(new SinglePresent<>(mDelegate));
    }


    @Override
    public FluentOptional<T> conditional(Predicate<T> predicate)
    {
        return new org.dmfs.jems.fluent.optional.Fluent<>(new Conditional<T>(predicate, mDelegate));
    }


    @Override
    public FluentIterable<T> iterable()
    {
        return new org.dmfs.jems.fluent.iterable.Fluent<>(new SingleIterable<>(mDelegate));
    }


    @Override
    public T value()
    {
        return mDelegate.value();
    }
}
