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

package org.dmfs.jems2.predicate;

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.predicate.PredicateMatcher.satisfiedBy;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class InTest
{
    @Test
    public void test()
    {
        assertThat(
            new In<>(1, 3),
            is(allOf(
                not(satisfiedBy(0)),
                satisfiedBy(1),
                satisfiedBy(2),
                satisfiedBy(3),
                not(satisfiedBy(4)))));

        assertThat(
            new In<>(1, 1),
            is(allOf(
                not(satisfiedBy(0)),
                satisfiedBy(1),
                not(satisfiedBy(2)))));

        assertThat(
            new In<>(1, 0),
            is(allOf(
                not(satisfiedBy(0)),
                not(satisfiedBy(1)),
                not(satisfiedBy(2)))));
    }
}