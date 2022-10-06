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

package org.dmfs.jems2.mockito;

import org.dmfs.jems2.*;
import org.dmfs.jems2.function.Unchecked;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.mockito.doubles.TestDoubles;
import org.dmfs.jems2.procedure.Composite;
import org.dmfs.jems2.single.Reduced;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import static org.mockito.Mockito.*;


/**
 * A container of static methods for creating mocks in a single expression.
 */
public final class Mock
{
    /**
     * Return a mock of the given class. This takes a couple of {@link Procedure}s to set up the mock in a single expression.
     * <p>
     * Example
     *
     * <pre>{@code
     * Foo fooMock = mock(Foo.class,
     *     with(Foo::bar, returning("foobar")),
     *     with(f->f.baz(123), returning("foobaz123")));
     * }</pre>
     * <p>
     * Note, in contrast to {@link Mockito#mock(Class)}, mocks returned by this method fail with an {@link AssertionError}
     * when a non-mocked method is called.
     */
    @SafeVarargs
    public static <T> T mock(Class<T> clazz, Procedure<? super T>... methods)
    {
        T result = TestDoubles.failingMock(clazz);
        new Composite<>(methods).process(result);
        return result;
    }


    /**
     * Updates a given mock. This takes a couple of {@link Procedure}s to update the mock in a single expression.
     * <p>
     * Example
     *
     * <pre>{@code
     * update(foomock,
     *     with(Foo::bar, returning("foobar")),
     *     with(f->f.baz(123), returning("foobaz123")));
     * }</pre>
     * <p>
     */
    @SafeVarargs
    public static <T> T update(T mock, Procedure<? super T>... methods)
    {
        new Composite<>(methods).process(mock);
        return mock;
    }


    /**
     * Returns a {@link Procedure} which configures a mock by setting the expectation on a method call.
     * The result of this is supposed to be passed to {@link #mock(Class, Procedure[])}.
     */
    @SafeVarargs
    public static <T, V> Procedure<T> with(
        FragileFunction<? super T, ? extends V, ? extends Throwable> methodCall,
        StubGenerator<? extends V> stubGenerator,
        StubGenerator<? extends V>... stubGenerators)
    {
        return mock -> new Unchecked<>(methodCall).value(
            new Reduced<>(stubGenerator, (stubber, function) -> function.value(stubber), new Seq<>(stubGenerators)).value().when(mock));
    }


    /**
     * Returns a {@link Procedure} which configures a mock by setting the expectation on a void method call.
     * The result of this is supposed to be passed to {@link #mock(Class, Procedure[])}.
     */
    @SafeVarargs
    public static <T, V> Procedure<T> withVoid(
        FragileProcedure<? super T, ? extends Throwable> methodCall,
        StubGenerator<? extends V> stubGenerator,
        StubGenerator<? extends V>... stubGenerators)
    {
        return mock ->
            new org.dmfs.jems2.single.Unchecked<>(
                () -> {
                    methodCall.process(
                        new Reduced<>(stubGenerator, (stubber, function) -> function.value(stubber), new Seq<>(stubGenerators)).value().when(mock));
                    return null;
                }).value();
    }


    /**
     * Returns a {@link StubGenerator} that configures a {@link Stubber} to return the given values in the given order.
     *
     * @see Stubber#doReturn(Object, Object...)
     */
    @SafeVarargs
    public static <T> StubGenerator<T> returning(T value, T... values)
    {
        return new StubGenerator<T>()
        {
            @Override
            public Stubber value(Stubber stubber)
            {
                return stubber.doReturn(value, (Object[]) values);
            }


            @Override
            public Stubber next()
            {
                return doReturn(value, (Object[]) values);
            }
        };
    }


    /**
     * Returns a {@link StubGenerator} that configures a {@link Stubber} to throw the given {@link Throwable}s.
     *
     * @see Stubber#doThrow(Throwable...)
     */
    public static <T> StubGenerator<T> throwing(Throwable... exception)
    {
        return new StubGenerator<T>()
        {
            @Override
            public Stubber value(Stubber stubber)
            {
                return stubber.doThrow(exception);
            }


            @Override
            public Stubber next()
            {
                return doThrow(exception);
            }
        };
    }


    /**
     * Returns a {@link StubGenerator} that configures a {@link Stubber} to answer with the given {@link Answer}.
     *
     * @see Stubber#doAnswer(Answer)
     */
    public static <T> StubGenerator<T> answering(Answer<T> answer)
    {
        return new StubGenerator<T>()
        {
            @Override
            public Stubber value(Stubber stubber)
            {
                return stubber.doAnswer(answer);
            }


            @Override
            public Stubber next()
            {
                return doAnswer(answer);
            }
        };
    }


    /**
     * Returns a {@link StubGenerator} that configures a {@link Stubber} do nothing.
     *
     * @see Stubber#doNothing()
     */
    public static <T> StubGenerator<T> doingNothing()
    {
        return new StubGenerator<T>()
        {
            @Override
            public Stubber value(Stubber stubber)
            {
                return stubber.doNothing();
            }


            @Override
            public Stubber next()
            {
                return doNothing();
            }
        };
    }


    /**
     * A type that combines a {@link Stubber} {@link Generator} and {@link Function}
     */
    public interface StubGenerator<T> extends Generator<Stubber>, Function<Stubber, Stubber>
    {

    }
}
