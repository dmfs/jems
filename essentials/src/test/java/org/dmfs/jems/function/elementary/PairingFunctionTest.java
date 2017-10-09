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

package org.dmfs.jems.function.elementary;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link PairingFunction}
 *
 * @author Gabor Keszthelyi
 */
public final class PairingFunctionTest
{

    @Test
    public void test()
    {
        LeftHand leftHand = new LeftHand();
        RightHand rightHand = new RightHand();

        PairingFunction<LeftHand, RightHand> pairingFunction = new PairingFunction<>();
        assertThat(pairingFunction.value(leftHand, rightHand).left(), sameInstance(leftHand));
        assertThat(pairingFunction.value(leftHand, rightHand).right(), sameInstance(rightHand));

        assertThat(PairingFunction.<LeftHand, RightHand>instance().value(leftHand, rightHand).left(), sameInstance(leftHand));
        assertThat(PairingFunction.<LeftHand, RightHand>instance().value(leftHand, rightHand).right(), sameInstance(rightHand));
    }


    private static final class LeftHand
    {

    }


    private static final class RightHand
    {

    }

}