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

package org.dmfs.jems2.hamcrest.matchers.procedure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.dmfs.jems2.hamcrest.matchers.iterable.IterableMatcher.iteratesTo;
import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.*;
import static org.dmfs.jems2.hamcrest.matchers.procedure.ProcedureMatcher2.processes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;


/**
 * Unit test for {@link ProcedureMatcher2}.
 */
public class ProcedureMatcher2Test
{
    @Test
    public void test()
    {
        assertThat(processes(ArrayList::new, "x", iteratesTo("x")),
            allOf(
                matches(l -> l::add),
                mismatches(l -> argument -> l.add(argument + "2"), "processed parameter item 0: was \"x2\""),
                mismatches(l -> arg -> {
                }, "processed parameter no item was \"x\""),
                describesAs("processes <[]> with \"x\" to iterable containing [\"x\"]")));
    }
}