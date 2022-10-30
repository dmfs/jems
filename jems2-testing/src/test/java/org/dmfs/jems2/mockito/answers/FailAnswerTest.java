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

package org.dmfs.jems2.mockito.answers;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;


/**
 * Unit test for {@link FailAnswer}.
 */
public class FailAnswerTest
{
    @Test
    public void test()
    {
        assertThat(() -> new FailAnswer().answer(mock(InvocationOnMock.class, invocation -> {
            if (!"toString".equals(invocation.getMethod().getName()))
            {
                throw new RuntimeException("Unexpected call on mock object");
            }
            else
            {
                return "invocationName";
            }
        })), is(throwing(AssertionError.class)));
    }
}