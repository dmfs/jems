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

package org.dmfs.jems2.hamcrest.matchers.fragile;

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.*;
import static org.dmfs.jems2.hamcrest.matchers.throwable.ThrowableMatcher.throwable;
import static org.dmfs.jems2.hamcrest.matchers.throwable.ThrowableMatcher.withMessage;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Test {@link FragileMatcher}.
 */
public class BrokenFragileMatcherTest
{

    @Test
    public void test()
    {
        assertThat(throwing(RuntimeException.class), matches(() -> {
            throw new RuntimeException();
        }));
        assertThat(throwing(RuntimeException.class), matches(() -> {
            throw new IllegalArgumentException();
        }));
        assertThat(throwing(instanceOf(RuntimeException.class)), matches(() -> {
            throw new RuntimeException();
        }));
        assertThat(throwing(instanceOf(RuntimeException.class)), matches(() -> {
            throw new IllegalArgumentException();
        }));

        assertThat(throwing(throwable(RuntimeException.class, withMessage("message"))), matches(() -> {
            throw new RuntimeException("message");
        }));
        assertThat(throwing(throwable(RuntimeException.class, withMessage("message"))), matches(() -> {
            throw new IllegalArgumentException("message");
        }));
        assertThat(throwing(throwable(RuntimeException.class)), matches(() -> {
            throw new RuntimeException();
        }));
        assertThat(throwing(throwable(RuntimeException.class)), matches(() -> {
            throw new IllegalArgumentException();
        }));

        assertThat(throwing(IllegalStateException.class), mismatches(() -> {
            throw new IllegalArgumentException();
        }, "broken Fragile threw <java.lang.IllegalArgumentException> is a java.lang.IllegalArgumentException"));
        assertThat(throwing(instanceOf(IllegalStateException.class)), mismatches(() -> {
            throw new IllegalArgumentException();
        }, "broken Fragile threw <java.lang.IllegalArgumentException> is a java.lang.IllegalArgumentException"));
        assertThat(throwing(RuntimeException.class), mismatches(() -> "x", "Fragile was not broken"));
        assertThat(throwing(throwable(RuntimeException.class)), mismatches(() -> "x", "Fragile was not broken"));

        assertThat(throwing(RuntimeException.class), describesAs("broken Fragile throwing an instance of java.lang.RuntimeException"));
        assertThat(throwing(throwable(RuntimeException.class)), describesAs("broken Fragile throwing an instance of java.lang.RuntimeException"));
    }
}