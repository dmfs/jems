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

package org.dmfs.iterables.decorators;

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.iterables.FluentIterable;
import org.dmfs.iterators.Filter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link Fluent}.
 *
 * @author Gabor Keszthelyi
 */
public class FluentIterableTest
{

    @Test
    public void filtered_empty_iterable() throws Exception
    {

        FluentIterable<String> empty = new Fluent<>(
                EmptyIterable.<String>instance()).filtered(
                new Filter<String>()
                {
                    @Override
                    public boolean iterate(String element)
                    {
                        return true;
                    }
                });
        assertFalse(empty.iterator().hasNext());
    }


    @Test
    public void filtered_none_pass() throws Exception
    {
        List<String> testList = Arrays.asList("1", "2", "3", "10", "20", "30", "100", "200", "300");

        FluentIterable<String> none = new Fluent<>(testList)
                .filtered(new Filter<String>()
                {
                    @Override
                    public boolean iterate(String argument)
                    {
                        return false;
                    }
                });

        assertFalse(none.iterator().hasNext());

    }


    @Test
    public void filtered_some_pass() throws Exception
    {
        List<String> testList = Arrays.asList("1", "2", "3", "10", "20", "30", "100", "200", "300");

        FluentIterable<String> fluent = new Fluent<>(testList)
                .filtered(new Filter<String>()
                {
                    @Override
                    public boolean iterate(String element)
                    {
                        return element.length() != 2;
                    }
                });

        assertThat(fluent, contains("1", "2", "3", "100", "200", "300"));
    }


    @Test
    public void filtered_all_pass() throws Exception
    {
        List<String> testList = Arrays.asList("1", "2", "3", "10", "20", "30", "100", "200", "300");

        FluentIterable<String> fluent = new Fluent<>(testList)
                .filtered(
                        new Filter<String>()
                        {
                            @Override
                            public boolean iterate(String element)
                            {
                                return true;
                            }
                        });

        assertThat(fluent, contains("1", "2", "3", "10", "20", "30", "100", "200", "300"));
    }

}