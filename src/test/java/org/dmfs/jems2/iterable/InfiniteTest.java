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

package org.dmfs.jems2.iterable;

import org.dmfs.jems2.Generatable;
import org.dmfs.jems2.Generator;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


/**
 * Test {@link Infinite}.
 */
public final class InfiniteTest
{
    @Test
    public void test()
    {
        // just like with the Infinite Iterable we can not test infinite results, thus the test looks pretty much like org.dmfs.jems.iterator.adapters.InfiniteTest.
        AtomicReference<Object> reference = new AtomicReference<>(new Object());

        Generatable<Object> dummyGeneratable = mock(Generatable.class);
        // return a dummy Generator which returns the atomic reference
        doReturn((Generator) reference::get).when(dummyGeneratable).generator();

        Iterator<Object> infinityIterator = new Infinite<>(dummyGeneratable).iterator();

        for (int i = 0; i < 100; ++i)
        {
            assertThat(infinityIterator.hasNext(), is(true));
            assertThat(infinityIterator.next(), sameInstance(reference.get()));

            // prepare a new result
            reference.set(new Object());
        }
    }
}