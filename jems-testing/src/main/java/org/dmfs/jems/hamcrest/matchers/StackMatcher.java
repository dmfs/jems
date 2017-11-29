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

package org.dmfs.jems.hamcrest.matchers;

import org.dmfs.iterables.decorators.Mapped;
import org.dmfs.iterables.elementary.Seq;
import org.dmfs.iterators.Function;
import org.dmfs.jems.stack.Stack;
import org.dmfs.optional.Optional;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Iterator;
import java.util.LinkedList;

import static org.dmfs.jems.hamcrest.matchers.PresentMatcher.isPresent;
import static org.hamcrest.Matchers.is;


/**
 * A {@link Matcher} which matches an entire {@link Stack}.
 *
 * @author Marten Gajda
 */
public final class StackMatcher<E> extends TypeSafeDiagnosingMatcher<Stack<E>>
{
    private final Iterable<Matcher<E>> mDelegates;


    @SafeVarargs
    public static <E> Matcher<Stack<E>> stacked(E... elements)
    {
        return new StackMatcher<>(new Mapped<>(new Seq<>(elements), new Function<E, Matcher<E>>()
        {
            @Override
            public Matcher<E> apply(E argument)
            {
                return is(argument);
            }
        }));
    }


    @SafeVarargs
    public static <E> Matcher<Stack<E>> stacked(Matcher<E>... elements)
    {
        return new StackMatcher<>(new Seq<>(elements));
    }


    public StackMatcher(Iterable<Matcher<E>> mDelegates)
    {
        this.mDelegates = mDelegates;
    }


    @Override
    protected boolean matchesSafely(Stack<E> item, Description mismatchDescription)
    {
        Iterator<Matcher<E>> matcherIterator = mDelegates.iterator();
        Stack<E> next = item;
        if (matcherIterator.hasNext())
        {
            Matcher<E> elementMatcher = matcherIterator.next();
            Optional<Stack.StackTop<E>> stackTop = next.top();

            // match the current element and the remainder of the stack
            Matcher<Optional<Stack.StackTop<E>>> stackTopMatcher =
                    isPresent(
                            new StackTopMatcher<>(
                                    elementMatcher,
                                    new StackMatcher<>(withoutFirst(mDelegates))));
            if (!stackTopMatcher.matches(stackTop))
            {
                stackTopMatcher.describeMismatch(stackTop, mismatchDescription);
                return false;
            }
        }
        else
        {
            Matcher<Optional<Stack.StackTop<E>>> absentMatcher = new AbsentMatcher<>();
            if (!absentMatcher.matches(next.top()))
            {
                mismatchDescription.appendText("Stack contains additional elements: ");
                absentMatcher.describeMismatch(next.top(), mismatchDescription);
                return false;
            }
        }
        return true;
    }


    /**
     * Returns the given {@link Iterable} without the first element.
     *
     * @param delegates
     *
     * @return
     */
    private Iterable<Matcher<E>> withoutFirst(Iterable<Matcher<E>> delegates)
    {
        LinkedList<Matcher<E>> list = new LinkedList<>();
        for (Matcher<E> matcher : delegates)
        {
            list.addLast(matcher);
        }
        list.removeFirst();
        return list;
    }


    @Override
    public void describeTo(Description description)
    {
        description.appendText("A Stack with elements matching [");
        boolean first = true;
        for (Matcher<E> matcher : mDelegates)
        {
            if (first)
            {
                first = false;
            }
            else
            {
                description.appendText(", ");
            }
            matcher.describeTo(description);
        }
        description.appendText("]");
    }
}
