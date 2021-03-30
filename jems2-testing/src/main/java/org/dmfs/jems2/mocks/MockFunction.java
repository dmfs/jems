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

package org.dmfs.jems2.mocks;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Pair;
import org.dmfs.jems2.iterable.Paired;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.iterable.Sieved;
import org.dmfs.jems2.iterable.SingletonIterable;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.pair.ValuePair;
import org.hamcrest.Matcher;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.sameInstance;


/**
 * Mock {@link Function} that can be used in tests as a convenience instead of 'manually' set up expectations for (multiple) calls.
 * <p>
 * It can be set up with Argument-Value {@link Pair}s so that when it is called it returns the Value from the {@link Pair} of the matching Argument.
 * <p>
 * For invocations with arguments that don't match any of the provided ones, it throws {@link AssertionError}.
 */
public final class MockFunction<Argument, Value> implements Function<Argument, Value>
{
    private final Iterable<Pair<Matcher<Argument>, Value>> mPairs;


    public MockFunction(Iterable<Pair<Matcher<Argument>, Value>> pairs)
    {
        mPairs = pairs;
    }


    @SafeVarargs
    public MockFunction(Pair<Matcher<Argument>, Value>... pairs)
    {
        this(new Seq<>(pairs));
    }


    public MockFunction(Iterable<Matcher<Argument>> args, Iterable<Value> values)
    {
        this(new Paired<>(args, values));
    }


    public MockFunction(Matcher<Argument> argumentMatcher, Value value)
    {
        this(new SingletonIterable<Pair<Matcher<Argument>, Value>>(new ValuePair<>(argumentMatcher, value)));
    }


    public MockFunction(Argument argument, Value value)
    {
        this(sameInstance(argument), value);
    }


    @Override
    public Value value(final Argument argument)
    {
        try
        {
            return new First<>(
                new Sieved<>(
                    pair -> pair.left().matches(argument),
                    mPairs)).value().right();
        }
        catch (NoSuchElementException e)
        {
            throw new AssertionError("MockFunction called with unexpected argument: " + argument);
        }
    }
}
