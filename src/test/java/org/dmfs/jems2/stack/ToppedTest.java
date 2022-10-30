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

package org.dmfs.jems2.stack;

import org.dmfs.jems2.Stack;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.dmfs.jems2.hamcrest.matchers.stack.StackTopMatcher.stackTop;
import static org.dmfs.jems2.mockito.doubles.TestDoubles.dummy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;


/**
 *
 */
public class ToppedTest
{

    @Test
    public void test()
    {
        Stack<String> dummyStack = dummy(Stack.class);
        assertThat(new Topped<>("1", dummyStack).top(), is(present(stackTop(is("1"), sameInstance(dummyStack)))));
    }
}