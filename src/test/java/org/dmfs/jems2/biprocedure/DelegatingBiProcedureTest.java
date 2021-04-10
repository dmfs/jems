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

package org.dmfs.jems2.biprocedure;

import org.dmfs.jems2.Procedure;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.mockito.MockInteractionMatcher.called;
import static org.dmfs.jems2.hamcrest.matchers.procedure.ProcedureMatcher.processes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;


/**
 * Test {@link DelegatingBiProcedure}.
 */
public class DelegatingBiProcedureTest
{
    @Test
    public void test()
    {
        assertThat(a ->
                new DelegatingBiProcedure<Procedure<String>, String>(Procedure::process)
                {
                }.process(a, "123"),
            processes(
                () -> (Procedure<String>) mock(Procedure.class),
                called(p -> p.process("123"))));
    }
}