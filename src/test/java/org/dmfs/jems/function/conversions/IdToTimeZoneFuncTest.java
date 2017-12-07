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
 * Unit test for {@link IdToTimeZoneFunc}.
 *
 * @author Gabor Keszthelyi
 */
public final class IdToTimeZoneFuncTest
{
    @Test
    public void test()
    {
        assertThat(IdToTimeZoneFunc.INST.value("Europe/Berlin").getID(), is("Europe/Berlin"));
    }


    @Test
    public void test_invalidId_returnsGMT()
    {
        assertThat(IdToTimeZoneFunc.INST.value("an invalid id").getID(), is("GMT"));
    }
}