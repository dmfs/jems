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
import org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher;
import org.dmfs.jems2.hamcrest.matchers.function.FragileFunctionMatcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.dmfs.jems2.hamcrest.matchers.LambdaMatcher.having;
import static org.dmfs.jems2.hamcrest.matchers.function.FragileFunctionMatcher.associates;
import static org.dmfs.jems2.hamcrest.matchers.generatable.GeneratableMatcher.startsWith;
import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.dmfs.jems2.hamcrest.matchers.procedure.ProcedureMatcher.processes;
import static org.dmfs.jems2.hamcrest.matchers.throwable.ThrowableMatcher.throwable;
import static org.dmfs.jems2.mockito.Mock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;


public class MockTest
{
    @Test
    public void testFailingMock()
    {
        assertThat((Single<?>) mock(Single.class), is(BrokenFragileMatcher.throwing(AssertionError.class)));

        assertThat((Function<Object, ?>) mock(Function.class),
            having(f -> () -> f.value(new Object()), is(BrokenFragileMatcher.throwing(AssertionError.class))));

        assertThat((Procedure<Object>) mock(Procedure.class),
            having(f -> () -> {
                f.process(new Object());
                return null;
            }, is(BrokenFragileMatcher.throwing(AssertionError.class))));
    }


    @Test
    public void testMockReturning()
    {
        assertThat(
            () -> mock(Generator.class, with(Generator::next, returning("1", "2", "3"), returning("4"), returning("5"))),
            startsWith("1", "2", "3", "4", "5", "5", "5"));

        assertThat((Optional<String>)
                mock(Optional.class,
                    with(Optional::value, returning("1")),
                    with(Optional::isPresent, returning(true))),
            is(present("1")));

        assertThat((Function<String, String>)
                mock(Function.class,
                    with(f -> f.value("1"), returning("one")),
                    with(f -> f.value("2"), returning("two")),
                    with(f -> f.value("3"), returning("three"))),
            allOf(
                associates("1", "one"),
                associates("2", "two"),
                associates("3", "three")));
    }


    @Test
    public void testNamedMock()
    {

        assertThat((Function<String, String>)
                mock("My Function", Function.class,
                    with(f -> f.value("1"), returning("one")),
                    with(f -> f.value("2"), returning("two")),
                    with(f -> f.value("3"), returning("three"))),
            allOf(
                associates("1", "one"),
                associates("2", "two"),
                associates("3", "three"),
                hasToString("My Function")));
    }


    @Test
    public void testMockThrowing()
    {
        assertThat((Fragile<?, ?>)
                mock(Fragile.class, with(Fragile::value,
                    throwing(new IOException()))),
            is(BrokenFragileMatcher.throwing(IOException.class)));

        assertThat((Function<String, String>)
                mock(Function.class,
                    with(f -> f.value("1"), returning("one")),
                    with(f -> f.value("2"), throwing(new IllegalArgumentException())),
                    with(f -> f.value("3"), returning("three"))),
            allOf(
                associates("1", "one"),
                FragileFunctionMatcher.throwing("2", throwable(IllegalArgumentException.class)),
                associates("3", "three")));
    }


    @Test
    public void testMockAnswering()
    {
        assertThat(
            () -> mock(Generator.class, with(Generator::next, answering(invocation -> "1"), answering(invocation -> "2"))),
            startsWith("1", "2"));
    }


    @Test
    public void testMockDoingNothing()
    {
        assertThat((Procedure<String>) mock(Procedure.class,
                withVoid(p -> p.process("123"), doingNothing(), doingNothing(), throwing(new IllegalArgumentException()))),
            allOf(
                processes(() -> "123", is("123")),
                processes(() -> "123", is("123")),
                having(p -> () -> {
                    p.process("123");
                    return null;
                }, is(BrokenFragileMatcher.throwing(IllegalArgumentException.class)))));
    }


    @Test
    public void testUpdateMock()
    {
        assertThat((Function<String, String>)
                update(mock(Function.class,
                        with(f -> f.value("1"), returning("one")),
                        with(f -> f.value("2"), returning("two")),
                        with(f -> f.value("3"), returning("three"))),
                    with(f -> f.value("1"), returning("uno")),
                    with(f -> f.value("4"), returning("cuatro"))),
            allOf(
                associates("1", "uno"),
                associates("2", "two"),
                associates("3", "three"),
                associates("4", "cuatro")));

    }
}