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

package org.dmfs.jems.stack.elementary;

import org.dmfs.jems.hamcrest.matchers.StackMatcher;
import org.dmfs.jems.single.elementary.ValueSingle;
import org.junit.Test;

import static org.dmfs.jems.hamcrest.matchers.PresentMatcher.isPresent;
import static org.dmfs.jems.hamcrest.matchers.StackTopMatcher.stackTop;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class SingleStackTest
{
    @Test
    public void test() throws Exception
    {
        assertThat(
                new SingleStack<>("1").top(),
                isPresent(
                        stackTop(
                                is("1"),
                                StackMatcher.<String>emptyStack())));
        assertThat(
                new SingleStack<>(new ValueSingle<>("1")).top(),
                isPresent(
                        stackTop(
                                is("1"),
                                StackMatcher.<String>emptyStack())));
    }
}