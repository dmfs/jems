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

package org.dmfs.jems2.single;

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.dmfs.jems2.hamcrest.matchers.single.SingleMatcher.hasValue;
import static org.dmfs.jems2.hamcrest.matchers.throwable.ThrowableMatcher.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link Unchecked}.
 */
public class UncheckedTest
{
    @Test
    public void test()
    {
        assertThat(new Unchecked<>(() -> "a"), hasValue("a"));
        assertThat(new Unchecked<>("broken", () -> "a"), hasValue("a"));
        assertThat(new Unchecked<>(RuntimeException::new, () -> "a"), hasValue("a"));

        assertThat(
            () -> new Unchecked<>(() -> {
                throw new Exception();
            }).value(),
            is(throwing(RuntimeException.class)));

        assertThat(
            () -> new Unchecked<>("broken", () -> {
                throw new Exception();
            }).value(),
            is(throwing(throwable(
                RuntimeException.class,
                withMessage("broken"),
                causedBy(throwable(Exception.class))))));

        assertThat(
            () -> new Unchecked<>("broken", () -> {
                throw new RuntimeException();
            }).value(),
            is(throwing(throwable(
                RuntimeException.class,
                withMessage("broken"),
                causedBy(throwable(RuntimeException.class))))));

        assertThat(
            () -> new Unchecked<>(IllegalStateException::new, () -> {
                throw new Exception();
            }).value(),
            is(throwing(throwable(
                IllegalStateException.class,
                causedBy(throwable(Exception.class))))));
    }
}