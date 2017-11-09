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

package org.dmfs.jems.single.elementary;

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.iterables.SingletonIterable;
import org.dmfs.iterables.elementary.Seq;
import org.dmfs.jems.function.BiFunction;
import org.junit.Test;

import static org.dmfs.jems.mockito.doubles.TestDoubles.failingMock;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class ReducedTest
{
    @Test
    public void testEmptyIterable() throws Exception
    {
        Object dummy = new Object();
        assertThat(new Reduced(dummy, failingMock(BiFunction.class), EmptyIterable.instance()).value(), sameInstance(dummy));
    }


    @Test
    public void testSingletonIterable() throws Exception
    {
        assertThat(new Reduced<>("0", new BiFunction<String, String, String>()
        {
            @Override
            public String value(String s, String s2)
            {
                // append new element to reduced ones
                return s2 + s;
            }
        }, new SingletonIterable<String>("1")).value(), is("01"));
    }


    @Test
    public void testSeqIterable() throws Exception
    {
        assertThat(new Reduced<>("0", new BiFunction<String, String, String>()
        {
            @Override
            public String value(String s, String s2)
            {
                // append new element to reduced ones
                return s2 + s;
            }
        }, new Seq<>("1", "2", "3", "4")).value(), is("01234"));
    }

}