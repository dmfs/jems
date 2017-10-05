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

package org.dmfs.testutils.iter;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.Is.is;


/**
 * Tests for {@link IterTestUtils}.
 *
 * @author Gabor Keszthelyi
 */
public final class IterTestUtilsTest
{
    @Test
    public void test_asIterable()
    {
        Object e1 = new Object();
        Object e2 = new Object();
        Object e3 = new Object();

        assertThat(IterTestUtils.asIterable(e1), contains(e1));
        assertThat(IterTestUtils.asIterable(e1, e2, e3), contains(e1, e2, e3));
    }


    @Test
    public void test_asIterator_single()
    {
        Object e1 = new Object();

        Iterator<Object> iterator = IterTestUtils.asIterator(e1);
        assertThat(iterator.next(), sameInstance(e1));
        assertThat(iterator.hasNext(), is(false));
    }


    @Test
    public void test_asIterator_multiple()
    {
        Object e1 = new Object();
        Object e2 = new Object();
        Object e3 = new Object();

        Iterator<Object> iterator = IterTestUtils.asIterator(e1, e2, e3);
        assertThat(iterator.next(), sameInstance(e1));
        assertThat(iterator.next(), sameInstance(e2));
        assertThat(iterator.next(), sameInstance(e3));
        assertThat(iterator.hasNext(), is(false));
    }

}