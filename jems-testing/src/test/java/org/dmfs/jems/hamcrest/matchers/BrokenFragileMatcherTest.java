/*
 * Copyright 2018 dmfs GmbH
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

package org.dmfs.jems.hamcrest.matchers;

import org.dmfs.jems.fragile.Fragile;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.io.IOException;

import static org.dmfs.jems.hamcrest.matchers.BrokenFragileMatcher.isBroken;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Test {@link IntactFragileMatcher}.
 *
 * @author Marten Gajda
 */
public class BrokenFragileMatcherTest
{

    @Test
    public void testBrokenIntactDescribeMismatch() throws Exception
    {
        Description description = new StringDescription();
        isBroken(RuntimeException.class).describeMismatch((Fragile) () -> "456", description);
        assertThat(description.toString(),
                is("Fragile was not broken"));
    }


    @Test
    public void testBrokenExceptionDescribeMismatch() throws Exception
    {
        Description description = new StringDescription();
        isBroken(RuntimeException.class).describeMismatch((Fragile) () -> {
            throw new Exception();
        }, description);
        assertThat(description.toString(), is("broken Fragile threw java.lang.Exception"));
    }


    @Test
    public void testBrokenDescribeTo() throws Exception
    {
        Description description = new StringDescription();
        isBroken(RuntimeException.class).describeTo(description);
        assertThat(description.toString(), is("broken Fragile throwing java.lang.RuntimeException"));
    }


    @Test
    public void testIsFragile() throws Exception
    {
        // does not throw
        assertThat(isBroken(IOException.class).matches((Fragile) Object::new), is(false));

        // throws exception which is not a subtype
        assertThat(isBroken(IOException.class).matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(false));

        // throws same exception
        assertThat(isBroken(UnsupportedOperationException.class).matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(true));

        // throws subtype exception
        assertThat(isBroken(RuntimeException.class).matches((Fragile) () -> {
            throw new IllegalArgumentException();
        }), is(true));
    }

}