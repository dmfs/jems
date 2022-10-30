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

package org.dmfs.jems2.function;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.dmfs.jems2.hamcrest.matchers.function.FragileFunctionMatcher.associates;
import static org.dmfs.jems2.hamcrest.matchers.function.FragileFunctionMatcher.throwing;
import static org.dmfs.jems2.hamcrest.matchers.throwable.ThrowableMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


/**
 * Test {@link Unchecked}.
 */
public class UncheckedTest
{
    @Test
    public void test()
    {
        assertThat(
            new Unchecked<>(s -> s),
            associates(123, 123));

        assertThat(
            new Unchecked<>("Error", s -> s),
            associates(123, 123));

        assertThat(
            new Unchecked<>(e -> new IllegalArgumentException("fail", e), s -> s),
            associates(123, 123));

        assertThat(
            new Unchecked<>(s -> {throw new IOException();}),
            is(throwing(RuntimeException.class, allOf(withMessage("Function call failed"), causedBy(throwable(IOException.class))))));

        assertThat(
            new Unchecked<>("error", s -> {throw new IOException();}),
            is(throwing(RuntimeException.class, allOf(withMessage("error"), causedBy(throwable(IOException.class))))));

        assertThat(
            new Unchecked<>(e -> new IllegalArgumentException("fail", e), s -> {throw new IOException();}),
            is(throwing(IllegalArgumentException.class, allOf(withMessage("fail"), causedBy(throwable(IOException.class))))));
    }
}