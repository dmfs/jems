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

package org.dmfs.jems.pair.hamcrest;

import org.dmfs.jems.pair.elementary.ValuePair;
import org.dmfs.testutils.tools.AbstractValueObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.dmfs.jems.pair.hamcrest.PairMatcher.pair;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Unit test for {@link PairMatcher}.
 *
 * @author Gabor Keszthelyi
 */
public final class PairMatcherTest
{

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void test_isPairWithNoMatcher_defaultsToEqualTo()
    {
        assertThat(new ValuePair<>(new LeftValueObject(1), new RightValueObject(5)),
                PairMatcher.pair(new LeftValueObject(1), new RightValueObject(5)));
    }


    @Test
    public void test_isPairWithMatcher()
    {
        Left left = new Left();
        Right right = new Right();

        assertThat(new ValuePair<>(left, right),
                pair(sameInstance(left), sameInstance(right)));

        assertThat(new ValuePair<>(left, new RightValueObject(3)),
                pair(sameInstance(left), equalTo(new RightValueObject(3))));

        assertThat(new ValuePair<>(new LeftValueObject(2), right),
                pair(equalTo(new LeftValueObject(2)), sameInstance(right)));
    }


    @Test
    public void test_isPair_failsForNotMatchingEquals()
    {
        exception.expect(AssertionError.class);
        exception.expectMessage("Expected: is Pair with left value: <LeftValueObject{2}> , and right value: <RightValueObject{5}>\n" +
                "     but: Left value doesn't match: was <LeftValueObject{1}>");

        assertThat(new ValuePair<>(new LeftValueObject(1), new RightValueObject(5)),
                PairMatcher.pair(new LeftValueObject(2), new RightValueObject(5)));
    }


    @Test
    public void test_isPair_failsForNotMatchingInstance()
    {
        exception.expect(AssertionError.class);
        exception.expectMessage(
                "Expected: is Pair with left value: sameInstance(<LeftValueObject{1}>) , and right value: sameInstance(<RightValueObject{5}>)\n" +
                        "     but: Left value doesn't match: was <LeftValueObject{1}>");

        assertThat(new ValuePair<>(new LeftValueObject(1), new RightValueObject(5)),
                pair(sameInstance(new LeftValueObject(1)), sameInstance(new RightValueObject(5))));
    }


    private static final class Left
    {

    }


    private static final class Right
    {

    }


    private static final class LeftValueObject extends AbstractValueObject
    {

        LeftValueObject(int value)
        {
            super(value);
        }
    }


    private static final class RightValueObject extends AbstractValueObject
    {

        RightValueObject(int value)
        {
            super(value);
        }
    }

}