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

package org.dmfs.jems2.hamcrest.matchers.fragile;

import org.dmfs.jems2.Fragile;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.fragile.FragileMatcher.hasValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Test {@link FragileMatcher}.
 */
public class FragileMatcherTest
{
    @Test
    public void testIntactValueDescribeMismatch()
    {
        Description description = new StringDescription();
        FragileMatcher.hasValue(is("123")).describeMismatch((Fragile) () -> "456", description);
        assertThat(description.toString(), is("intact Fragile was \"456\""));
    }


    @Test
    public void testIntactExceptionDescribeMismatch()
    {
        Description description = new StringDescription();
        FragileMatcher.hasValue(is("123")).describeMismatch((Fragile) () -> {
            throw new Exception();
        }, description);
        assertThat(description.toString(), is("broken throwing java.lang.Exception"));
    }


    @Test
    public void testIntactDescribeTo()
    {
        Description description = new StringDescription();
        FragileMatcher.hasValue(is("123")).describeTo(description);
        assertThat(description.toString(), is("intact Fragile is \"123\""));
    }


    @Test
    public void testHasValue()
    {
        // does not throw
        assertThat(hasValue("123").matches((Fragile<String, Exception>) () -> "123"), is(true));
        assertThat(FragileMatcher.hasValue(is("123")).matches((Fragile<String, Exception>) () -> "123"), is(true));

        assertThat(hasValue("456").matches((Fragile<String, Exception>) () -> "123"), is(false));
        assertThat(FragileMatcher.hasValue(is("456")).matches((Fragile<String, Exception>) () -> "123"), is(false));

        assertThat(hasValue("123").matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(false));
        assertThat(FragileMatcher.hasValue(is("123")).matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(false));
    }
}