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

package org.dmfs.jems.stack.decorators;

import org.dmfs.jems.hamcrest.matchers.StackMatcher;
import org.dmfs.jems.stack.Stack;
import org.junit.Test;

import static org.dmfs.jems.hamcrest.matchers.PresentMatcher.isPresent;
import static org.dmfs.jems.hamcrest.matchers.StackTopMatcher.stackTop;
import static org.dmfs.jems.mockito.doubles.TestDoubles.dummy;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class StackedTest
{
    @Test
    public void testSingle() throws Exception
    {
        assertThat(new Stacked<>("1").top(), isPresent(stackTop(is("1"), StackMatcher.<String>emptyStack())));
    }


    @Test
    public void testStacked() throws Exception
    {
        Stack<String> dummyStack = dummy(Stack.class);
        assertThat(new Stacked<>("1", dummyStack).top(), isPresent(stackTop(is("1"), sameInstance(dummyStack))));
    }
}