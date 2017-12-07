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
 * Unit test for {@link StringToIntFunc}.
 *
 * @author Gabor Keszthelyi
 */
public final class StringToIntFuncTest
{
    @Test
    public void test()
    {
        assertThat(StringToIntFunc.INST.value("233"), is(233));
    }


    @Test(expected = Exception.class)
    public void test_empty_throws()
    {
        StringToIntFunc.INST.value("");
    }


    @Test(expected = Exception.class)
    public void test_nonNumber_throws()
    {
        StringToIntFunc.INST.value("a");
    }
}