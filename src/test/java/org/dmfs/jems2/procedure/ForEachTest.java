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

package org.dmfs.jems2.procedure;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.iterable.EmptyIterable;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.Absent;
import org.dmfs.jems2.optional.Present;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.mockito.MockInteractionMatcher.calledInOrder;
import static org.dmfs.jems2.hamcrest.matchers.mockito.MockInteractionMatcher.notCalled;
import static org.dmfs.jems2.hamcrest.matchers.procedure.ProcedureMatcher.processes;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


/**
 * Unit test for {@link ForEach}.
 */
public class ForEachTest
{

    @Test
    public void testVarArgs()
    {
        assertThat(
            new ForEach<>(),
            processes(
                () -> (Procedure<? super Object>) mock(Procedure.class),
                is(notCalled())));
        assertThat(
            new ForEach<>("x"),
            processes(
                () -> (Procedure<? super String>) mock(Procedure.class),
                calledInOrder(p -> p.process("x"))));
        assertThat(
            new ForEach<>("x", "y", "z"),
            processes(
                () -> (Procedure<? super String>) mock(Procedure.class),
                calledInOrder(
                    p -> p.process("x"),
                    p -> p.process("y"),
                    p -> p.process("z"))));
    }


    @Test
    public void testIterables()
    {
        assertThat(
            new ForEach<>(EmptyIterable.emptyIterable()),
            processes(
                () -> (Procedure<? super Object>) mock(Procedure.class),
                is(notCalled())));
        assertThat(
            new ForEach<>(new Seq<>("x")),
            processes(
                () -> (Procedure<? super String>) mock(Procedure.class),
                calledInOrder(p -> p.process("x"))));
        assertThat(
            new ForEach<>(new Seq<>("x", "y", "z")),
            processes(
                () -> (Procedure<? super String>) mock(Procedure.class),
                calledInOrder(
                    p -> p.process("x"),
                    p -> p.process("y"),
                    p -> p.process("z"))));
    }


    @Test
    public void testOptional()
    {
        assertThat(
            new ForEach<>(new Absent<>()),
            processes(
                () -> (Procedure<? super Object>) mock(Procedure.class),
                is(notCalled())));
        assertThat(
            new ForEach<>(new Present<>("x")),
            processes(
                () -> (Procedure<? super String>) mock(Procedure.class),
                calledInOrder(p -> p.process("x"))));
    }


    @Test
    public void testSingle()
    {
        assertThat(
            new ForEach<>(() -> "x"),
            processes(
                () -> (Procedure<? super String>) mock(Procedure.class),
                calledInOrder(p -> p.process("x"))));
    }
}