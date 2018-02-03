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

package org.dmfs.jems.fragile.composite;

import org.dmfs.jems.fragile.elementary.Broken;
import org.dmfs.jems.fragile.elementary.Intact;
import org.junit.Test;

import javax.net.ssl.SSLException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.dmfs.jems.hamcrest.matchers.FragileMatcher.isBroken;
import static org.dmfs.jems.hamcrest.matchers.FragileMatcher.isIntact;
import static org.junit.Assert.assertThat;


/**
 * Test {@link Zipped}.
 *
 * @author Marten Gajda
 */
public class ZippedTest
{
    @Test
    public void testValue() throws Exception
    {
        assertThat(new Zipped<>(new Intact<>("abc"), new Intact<>(123), (left, right) -> left + right), isIntact("abc123"));
        assertThat(new Zipped<>(new Intact<>("abc"), new Broken<>(new FileNotFoundException()), (left, right) -> left + right), isBroken(IOException.class));
        assertThat(new Zipped<>(new Broken<>(new SSLException("x")), new Intact<>(123), (String left, Integer right) -> left + right),
                isBroken(IOException.class));
        assertThat(new Zipped<>(new Broken<>(new SSLException("x")), new Broken<>(new FileNotFoundException()), (String left, String right) -> left + right),
                isBroken(IOException.class));
    }

}