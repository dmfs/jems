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
import org.junit.Test;

import java.io.IOException;

import static org.dmfs.jems.hamcrest.matchers.FragileMatcher.hasValue;
import static org.dmfs.jems.hamcrest.matchers.FragileMatcher.isFragile;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author marten
 */
public class FragileMatcherTest
{
    @Test
    public void testHasValue() throws Exception
    {
        // does not throw
        assertThat(hasValue("123").matches((Fragile<String, Exception>) () -> "123"), is(true));
        assertThat(hasValue(is("123")).matches((Fragile<String, Exception>) () -> "123"), is(true));

        assertThat(hasValue("456").matches((Fragile<String, Exception>) () -> "123"), is(false));
        assertThat(hasValue(is("456")).matches((Fragile<String, Exception>) () -> "123"), is(false));

        assertThat(hasValue("123").matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(false));
        assertThat(hasValue(is("123")).matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(false));
    }


    @Test
    public void testIsFragile() throws Exception
    {
        // does not throw
        assertThat(isFragile(IOException.class).matches((Fragile) Object::new), is(false));

        // throws exception which is not a subtype
        assertThat(isFragile(IOException.class).matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(false));

        // throws same exception
        assertThat(isFragile(UnsupportedOperationException.class).matches((Fragile) () -> {
            throw new UnsupportedOperationException();
        }), is(true));

        // throws subtype exception
        assertThat(isFragile(RuntimeException.class).matches((Fragile) () -> {
            throw new IllegalArgumentException();
        }), is(true));
    }

}