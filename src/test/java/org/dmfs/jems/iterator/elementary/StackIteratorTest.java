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

package org.dmfs.jems.iterator.elementary;

import org.dmfs.jems.stack.decorators.Stacked;
import org.dmfs.jems.stack.elementary.EmptyStack;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
 * @author Marten Gajda
 */
public class StackIteratorTest
{

    @Test
    public void testEmpty()
    {
        Iterator<String> testIterator = new StackIterator<>(new Stacked<>("1"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        try
        {
            new StackIterator<>(new EmptyStack<>()).next();
            fail("did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void testNonEmpty1()
    {
        Iterator<String> testIterator = new StackIterator<>(new Stacked<>("1"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("1"));
        assertThat(testIterator.hasNext(), is(false));
        assertThat(testIterator.hasNext(), is(false));
        assertThat(testIterator.hasNext(), is(false));

        try
        {
            new StackIterator<>(new EmptyStack<>()).next();
            fail("did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void testNonEmpty2()
    {
        Iterator<String> testIterator = new StackIterator<>(new Stacked<>("1", new Stacked<String>("2")));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("1"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("2"));
        assertThat(testIterator.hasNext(), is(false));
        assertThat(testIterator.hasNext(), is(false));
        assertThat(testIterator.hasNext(), is(false));

        try
        {
            new StackIterator<>(new EmptyStack<>()).next();
            fail("did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }
}