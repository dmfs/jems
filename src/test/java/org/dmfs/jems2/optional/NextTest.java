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

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.iterator.EmptyIterator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for {@link Next}.
 */
public class NextTest
{
    @Test
    public void test_whenIteratorIsEmpty_resultShouldBeAbsent()
    {
        Optional<String> result = new Next<>(EmptyIterator.<String>emptyIterator());

        assertFalse(result.isPresent());
        try
        {
            result.value();
            fail();
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }


    @Test
    public void test_whenIteratorHasNextValue_resultShouldBeThat()
    {
        Optional<String> result = new Next<>(Arrays.asList("a", "b", "c").iterator());

        assertTrue(result.isPresent());
        assertEquals("a", result.value());
    }


    @Test
    public void test_whenIteratorHasMovedAlready_resultShouldBeNext()
    {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        iterator.next();
        Optional<String> result = new Next<>(iterator);

        assertTrue(result.isPresent());
        assertEquals("b", result.value());
    }


    @Test
    public void test_whenIteratorReachedTheEnd_resultShouldBeAbsent()
    {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        Optional<String> result = new Next<>(iterator);

        assertFalse(result.isPresent());
        try
        {
            result.value();
            fail();
        }
        catch (NoSuchElementException e)
        {
            // pass
        }
    }

}