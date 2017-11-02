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

package org.dmfs.iterators.decorators;

import org.dmfs.iterators.EmptyIterator;
import org.dmfs.iterators.elementary.Seq;
import org.dmfs.jems.predicate.elementary.Anything;
import org.dmfs.jems.predicate.elementary.Equals;
import org.dmfs.jems.predicate.elementary.Nothing;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
 * @author Marten Gajda
 */
public class SievedTest
{
    @Test
    public void testEmptyDelegate()
    {
        Iterator<String> testIterator = new Sieved<>(new Equals<>("x"), EmptyIterator.<String>instance());

        assertThat(testIterator.hasNext(), is(false));

        try
        {
            testIterator.next();
            fail("Did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void testEmptyResults()
    {
        Iterator<String> testIterator = new Sieved<>(new Nothing<String>(), new Seq<>("1", "2", "3", "4"));

        assertThat(testIterator.hasNext(), is(false));

        try
        {
            testIterator.next();
            fail("Did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void testNormalUsage()
    {
        Iterator<String> testIterator = new Sieved<>(new Equals<>("x"), new Seq<>("x", "y", "x", "y"));

        // test normal usage
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("x"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("x"));
        assertThat(testIterator.hasNext(), is(false));

        try
        {
            testIterator.next();
            fail("Did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void testAllMatch()
    {
        Iterator<String> testIterator = new Sieved<>(new Anything<String>(), new Seq<>("x", "y", "x", "y"));

        // test normal usage
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("x"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("y"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("x"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("y"));
        assertThat(testIterator.hasNext(), is(false));

        try
        {
            testIterator.next();
            fail("Did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void testNoHasNext()
    {
        Iterator<String> testIterator = new Sieved<>(new Equals<>("x"), new Seq<>("x", "y", "x", "y"));

        // test behavior if hasNext is not called
        assertThat(testIterator.next(), is("x"));
        assertThat(testIterator.next(), is("x"));

        try
        {
            testIterator.next();
            fail("Did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void testMultipleHasNext()
    {
        Iterator<String> testIterator = new Sieved<>(new Equals<>("x"), new Seq<>("x", "y", "x", "y"));

        // call has next a few times to ensure it doesn't proceed to the next elements each time
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("x"));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is("x"));
        assertThat(testIterator.hasNext(), is(false));
        assertThat(testIterator.hasNext(), is(false));
        assertThat(testIterator.hasNext(), is(false));

        try
        {
            testIterator.next();
            fail("Did not throw");
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }
}