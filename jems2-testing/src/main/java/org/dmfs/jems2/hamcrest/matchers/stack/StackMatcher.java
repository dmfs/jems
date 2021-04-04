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

package org.dmfs.jems2.hamcrest.matchers.stack;

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Stack;
import org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.iterable.StackIterable;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.dmfs.jems2.hamcrest.matchers.optional.AbsentMatcher.absent;


/**
 * A {@link Matcher} which matches an entire {@link Stack}.
 */
public final class StackMatcher<T> extends FeatureMatcher<Stack<T>, Iterable<T>>
{

    public static <E> Matcher<Stack<E>> emptyStack()
    {
        return new FeatureMatcher<Stack<E>, Optional<Stack.StackTop<E>>>(absent(), "StackTop", "StackTop")
        {
            @Override
            protected Optional<Stack.StackTop<E>> featureValueOf(Stack<E> actual)
            {
                return actual.top();
            }
        };
    }


    @SafeVarargs
    public static <E> Matcher<Stack<E>> stacked(E... elements)
    {
        return new StackMatcher<>(IterableMatcher.iteratesTo(
            new Mapped<>(
                Matchers::is,
                new Seq<>(elements))));
    }


    @SafeVarargs
    public static <E> Matcher<Stack<E>> stacked(Matcher<E>... elements)
    {
        return new StackMatcher<>(IterableMatcher.iteratesTo(new Seq<>(elements)));
    }


    /**
     *
     */
    public StackMatcher(Matcher<Iterable<? extends T>> stackIterableMatcher)
    {
        super(stackIterableMatcher,
            "Elements in Stack",
            "Elements in Stack");
    }


    @Override
    protected Iterable<T> featureValueOf(Stack<T> actualStack)
    {
        return new StackIterable<>(actualStack);
    }
}