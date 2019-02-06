/*
 * Copyright 2019 dmfs GmbH
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

package org.dmfs.iterables;

import org.junit.Test;

import static org.dmfs.jems.hamcrest.matchers.IterableMatcher.iteratesTo;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link CsvIterable}.
 *
 * @author Marten Gajda
 */
public class CsvIterableTest
{
    @Test
    public void test()
    {
        assertThat(new CsvIterable("", ';'), iteratesTo(""));
        assertThat(new CsvIterable("a", ';'), iteratesTo("a"));
        assertThat(new CsvIterable("abc", ';'), iteratesTo("abc"));
        assertThat(new CsvIterable("abc;", ';'), iteratesTo("abc", ""));
        assertThat(new CsvIterable(";abc", ';'), iteratesTo("", "abc"));
        assertThat(new CsvIterable(";abc;", ';'), iteratesTo("", "abc", ""));
        assertThat(new CsvIterable("abc;xyz;123", ';'), iteratesTo("abc", "xyz", "123"));
        assertThat(new CsvIterable("abc;;xyz;;123", ';'), iteratesTo("abc", "", "xyz", "", "123"));
        assertThat(new CsvIterable(";abc;xyz;123;", ';'), iteratesTo("", "abc", "xyz", "123", ""));
        assertThat(new CsvIterable(";;abc;;xyz;;123;;", ';'), iteratesTo("", "", "abc", "", "xyz", "", "123", "", ""));

        assertThat(new CsvIterable("\"\"", ';'), iteratesTo("\"\""));
        assertThat(new CsvIterable("\"a\"", ';'), iteratesTo("\"a\""));
        assertThat(new CsvIterable("\"abc\"", ';'), iteratesTo("\"abc\""));
        assertThat(new CsvIterable("\"abc\";", ';'), iteratesTo("\"abc\"", ""));
        assertThat(new CsvIterable("\"abc;\"", ';'), iteratesTo("\"abc;\""));
        assertThat(new CsvIterable(";\"abc\"", ';'), iteratesTo("", "\"abc\""));
        assertThat(new CsvIterable("\";abc\"", ';'), iteratesTo("\";abc\""));
        assertThat(new CsvIterable(";\"abc\";", ';'), iteratesTo("", "\"abc\"", ""));
        assertThat(new CsvIterable("\";abc;\"", ';'), iteratesTo("\";abc;\""));
        assertThat(new CsvIterable("abc;\"xyz;123\"", ';'), iteratesTo("abc", "\"xyz;123\""));
        assertThat(new CsvIterable("\"abc;;xyz\";;123", ';'), iteratesTo("\"abc;;xyz\"", "", "123"));
        assertThat(new CsvIterable(";abc;\"xyz;123;\"", ';'), iteratesTo("", "abc", "\"xyz;123;\""));
        assertThat(new CsvIterable("\";;abc;;xyz;\";123;;", ';'), iteratesTo("\";;abc;;xyz;\"", "123", "", ""));

    }
}