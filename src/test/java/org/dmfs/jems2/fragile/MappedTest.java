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

package org.dmfs.jems2.fragile;

import org.dmfs.jems2.single.Just;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.dmfs.jems2.hamcrest.matchers.fragile.FragileMatcher.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Test {@link Mapped}.
 */
public class MappedTest
{
    @Test
    public void testValue()
    {
        assertThat(new Mapped<>(String::length, new Just<>("abcde")), hasValue(5));
        assertThat(new Mapped<>(String::length, new Broken<>(new FileNotFoundException())), is(throwing(FileNotFoundException.class)));
        assertThat(new Mapped<>(s -> {throw new FileNotFoundException();}, () -> "abcde"), is(throwing(FileNotFoundException.class)));
        assertThat(new Mapped<>(s -> {throw new IOException();}, new Broken<>(new FileNotFoundException())), is(throwing(FileNotFoundException.class)));
    }

}