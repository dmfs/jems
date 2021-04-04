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

import org.dmfs.jems2.Predicate;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.predicate.PredicateMatcher.satisfiedBy;
import static org.dmfs.jems2.mockito.doubles.TestDoubles.failingMock;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;


/**
 * Test {@link Not}.
 */
public class NotTest
{
    @Test
    public void testSatisfiedBy()
    {
        Predicate<String> mockPredicate = failingMock(Predicate.class);
        doReturn(true).when(mockPredicate).satisfiedBy("match");
        doReturn(false).when(mockPredicate).satisfiedBy("mismatch");

        assertThat(new Not<>(mockPredicate), is(not(satisfiedBy("match"))));
        assertThat(new Not<>(mockPredicate), is(satisfiedBy("mismatch")));
    }


    @Test
    public void testEqualsCtor()
    {
        assertThat(new Not<>("a"), is(not(satisfiedBy("a"))));
        assertThat(new Not<>("a"), is(satisfiedBy("b")));
    }

}