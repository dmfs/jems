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

package org.dmfs.jems.function.conversions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link BinaryStrToBool}.
 *
 * @author Gabor Keszthelyi
 */
public final class BinaryStrToBoolTest
{
    @Test
    public void test()
    {
        assertThat(BinaryStrToBool.FUNC.value("0"), is(false));
        assertThat(BinaryStrToBool.FUNC.value("1"), is(true));
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_empty_throws()
    {
        BinaryStrToBool.FUNC.value("");
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_2_throws()
    {
        BinaryStrToBool.FUNC.value("2");
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_minus1_throws()
    {
        BinaryStrToBool.FUNC.value("-1");
    }
}