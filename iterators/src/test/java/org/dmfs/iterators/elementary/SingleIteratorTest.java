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

package org.dmfs.iterators.elementary;

import org.dmfs.jems.single.elementary.ValueSingle;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
 * @author Marten Gajda
 */
public class SingleIteratorTest
{
    @Test
    public void testHasNext() throws Exception
    {
        Iterator<Object> iterator = new SingleIterator<>(new ValueSingle<>(new Object()));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.hasNext(), is(false));
    }


    @Test
    public void testNext() throws Exception
    {
        Object dummyObject = new Object();
        Iterator<Object> iterator = new SingleIterator<>(new ValueSingle<>(dummyObject));
        assertThat(iterator.next(), is(dummyObject));

        try
        {
            iterator.next();
            fail();
        }
        catch (NoSuchElementException e)
        {
            // test passed
        }
    }

}