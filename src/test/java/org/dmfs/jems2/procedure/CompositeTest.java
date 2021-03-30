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

import org.dmfs.jems2.Generator;
import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.iterable.Seq;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.dmfs.jems2.hamcrest.matchers.mockito.MockInteractionMatcher.calledInOrder;
import static org.dmfs.jems2.hamcrest.matchers.procedure.ProcedureMatcher2.processes;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


/**
 * Unit test for {@link Composite}.
 */
public class CompositeTest
{
    @Test
    public void testIterableCtor()
    {
        assertThat(delegates -> new Composite<>(delegates),
            allOf(
                processes(
                    (Generator<Seq<Procedure<String>>>) Seq::new,
                    "x",
                    is(emptyIterable())),
                processes(
                    () -> new Seq<Procedure<String>>(mock(Procedure.class)),
                    "x",
                    iteratesTo(
                        is(calledInOrder(p -> p.process("x"))))),
                processes(
                    () -> new Seq<Procedure<String>>(mock(Procedure.class), mock(Procedure.class)),
                    "x",
                    iteratesTo(
                        is(calledInOrder(p -> p.process("x"))),
                        is(calledInOrder(p -> p.process("x")))))));
    }


    @Test
    public void testVarargCtor()
    {
        assertThat(delegates -> new Composite<>(delegates),
            allOf(
                processes(
                    () -> new Procedure[0],
                    "x",
                    is(emptyArray())),
                processes(
                    () -> new Procedure[] { mock(Procedure.class) },
                    "x",
                    arrayContaining(
                        is(calledInOrder(p -> p.process("x"))))),
                processes(
                    () -> new Procedure[] { mock(Procedure.class), mock(Procedure.class) },
                    "x",
                    arrayContaining(
                        is(calledInOrder(p -> p.process("x"))),
                        is(calledInOrder(p -> p.process("x")))))));
    }
}