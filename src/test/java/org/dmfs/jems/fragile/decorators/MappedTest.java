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

package org.dmfs.jems.fragile.decorators;

import org.dmfs.jems.fragile.elementary.Broken;
import org.dmfs.jems.fragile.elementary.Intact;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.dmfs.jems.hamcrest.matchers.BrokenFragileMatcher.isBroken;
import static org.dmfs.jems.hamcrest.matchers.IntactFragileMatcher.isIntact;
import static org.junit.Assert.assertThat;


/**
 * Test {@link Mapped}.
 *
 * @author Marten Gajda
 */
public class MappedTest
{
    @Test
    public void testValue() throws Exception
    {
        assertThat(new Mapped<>(String::length, new Intact<>("abcde")), isIntact(5));
        assertThat(new Mapped<>(String::length, new Broken<>(new FileNotFoundException())), isBroken(FileNotFoundException.class));
    }

}