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

import org.dmfs.jems.pair.Pair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.dmfs.jems.pair.hamcrest.PairMatcher.pair;
import static org.dmfs.testutils.TestDoubles.failingMock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.Mockito.doReturn;


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
    public void test_pairWithAndWithoutMatcher_withDifferentArgPermutations()
    {
        assertThat(pair(equalTo(new String("a")), equalTo(new Integer(1)))
                .matches(mockPair(new String("a"), new Integer(1))), is(true));
        assertThat(pair(new String("a"), new Integer(1))
                .matches(mockPair(new String("a"), new Integer(1))), is(true));

        assertThat(pair(equalTo(new String("b")), equalTo(new Integer(1)))
                .matches(mockPair(new String("a"), new Integer(1))), is(false));
        assertThat(pair(new String("b"), new Integer(1))
                .matches(mockPair(new String("a"), new Integer(1))), is(false));

        assertThat(pair(equalTo(new String("a")), equalTo(new Integer(2)))
                .matches(mockPair(new String("a"), new Integer(1))), is(false));
        assertThat(pair(new String("a"), new Integer(2))
                .matches(mockPair(new String("a"), new Integer(1))), is(false));

        assertThat(pair(equalTo(new String("b")), equalTo(new Integer(2)))
                .matches(mockPair(new String("a"), new Integer(1))), is(false));
        assertThat(pair(new String("b"), new Integer(2))
                .matches(mockPair(new String("a"), new Integer(1))), is(false));
    }


    @Test
    public void test_pairWithoutMatcher_whenFailsForNotMatchingLeft_shouldThrowWithCorrectMessage()
    {
        exception.expect(AssertionError.class);
        exception.expectMessage("Expected: is Pair with left value: \"a\" , and right value: <1>\n" +
                "     but: Left value doesn't match: was \"b\"");

        assertThat(mockPair(new String("b"), new Integer(1)),
                pair(new String("a"), new Integer(1)));
    }


    @Test
    public void test_pairWithMatcher_whenFailsForNotMatchingRight_shouldThrowWithCorrectMessage()
    {
        exception.expect(AssertionError.class);
        exception.expectMessage("Expected: is Pair with left value: \"a\" , and right value: sameInstance(<1>)\n" +
                "     but: Right value doesn't match: was <1>");

        assertThat(mockPair(new String("a"), new Integer(1)),
                pair(equalTo(new String("a")), sameInstance(new Integer(1))));
    }


    private <L, R> Pair<L, R> mockPair(L left, R right)
    {
        Pair<L, R> mockPair = failingMock(Pair.class);
        doReturn(left).when(mockPair).left();
        doReturn(right).when(mockPair).right();
        return mockPair;
    }

}