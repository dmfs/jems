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
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.predicate.PredicateMatcher.satisfiedBy;
import static org.dmfs.jems2.mockito.doubles.TestDoubles.dummy;
import static org.dmfs.jems2.mockito.doubles.TestDoubles.failingMock;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


/**
 * Test {@link DelegatingPredicate}.
 */
public class DelegatingPredicateTest
{
    @Test
    public void testSatisfiedBy()
    {
        Object testDummy = dummy(Object.class);
        Predicate<Object> mockPredicate = failingMock(Predicate.class);
        doReturn(false).when(mockPredicate).satisfiedBy(any());
        doReturn(true).when(mockPredicate).satisfiedBy(testDummy);

        assertThat(new TestPredicate<>(mockPredicate), is(not(satisfiedBy(new Object()))));
        assertThat(new TestPredicate<>(mockPredicate), is(satisfiedBy(testDummy)));
    }


    private final static class TestPredicate<T> extends DelegatingPredicate<T>
    {
        public TestPredicate(Predicate<T> delegate)
        {
            super(delegate);
        }
    }
}