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

package org.dmfs.jems2.optional;

import org.dmfs.jems2.Predicate;
import org.dmfs.jems2.iterable.EmptyIterable;
import org.dmfs.jems2.iterable.Seq;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.optional.AbsentMatcher.absent;
import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.dmfs.jems2.mockito.doubles.TestDoubles.failingMock;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;


/**
 * Unit test for {@link First}.
 */
public class FirstTest
{
    @Test
    public void testFirst()
    {
        assertThat(new First<>(EmptyIterable.<String>emptyIterable()), is(absent()));
        assertThat(new First<>(new Seq<>("test")), is(present("test")));
        assertThat(new First<>(new Seq<>("test", "test123")), is(present("test")));
    }


    @Test
    public void testSievedFirst()
    {
        Predicate<String> mockPredicate = failingMock(Predicate.class);
        doReturn(false).when(mockPredicate).satisfiedBy(anyString());
        doReturn(true).when(mockPredicate).satisfiedBy("test123");

        assertThat(new First<>(mockPredicate, EmptyIterable.<String>emptyIterable()), is(absent()));
        assertThat(new First<>(mockPredicate, new Seq<>("test")), is(absent()));
        assertThat(new First<>(mockPredicate, new Seq<>("test", "test123")), is(present("test123")));
    }
}