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

package org.dmfs.jems2.iterator;

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.emptyIterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Test {@link EmptyIterator}.
 */
public class EmptyIteratorTest
{
    @Test
    public void test()
    {
        assertThat(EmptyIterator::new,
            is(emptyIterator()));

        assertThat(EmptyIterator::emptyIterator,
            is(emptyIterator()));
    }
}