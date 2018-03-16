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

package org.dmfs.jems.fluent.stack;

import org.dmfs.jems.fluent.FluentIterable;
import org.dmfs.jems.fluent.FluentStack;
import org.dmfs.jems.iterable.elementary.StackIterable;
import org.dmfs.jems.stack.Stack;
import org.dmfs.jems.stack.decorators.Topped;
import org.dmfs.optional.Optional;


/**
 * @author Marten Gajda
 */
public final class Fluent<T> implements FluentStack<T>
{
    private final Stack<T> mDelegate;


    public Fluent(Stack<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public FluentStack<T> topped(T element)
    {
        return new Fluent<>(new Topped<>(element, mDelegate));
    }


    @Override
    public FluentIterable<T> allFromTop()
    {
        return new org.dmfs.jems.fluent.iterable.Fluent<>(new StackIterable<>(mDelegate));
    }


    @Override
    public Optional<StackTop<T>> top()
    {
        return mDelegate.top();
    }
}
