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

package org.dmfs.jems2.mocks;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Pair;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.pair.ValuePair;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Unit test for {@link MockFunction}.
 */
public final class MockFunctionTest
{

    private static final Object RES_1 = new Object();
    private static final Object RES_2 = new Object();
    private static final Object RES_3 = new Object();


    @Test
    public void test_priCtorIterablePairs_matchingArgs_pass()
    {
        MockFunction<Integer, Object> mockFunction = new MockFunction<>(
            new Seq<Pair<Matcher<Integer>, Object>>(
                new ValuePair<>(equalTo(arg(1)), RES_1),
                new ValuePair<>(equalTo(arg(2)), RES_2),
                new ValuePair<>(equalTo(arg(3)), RES_3)
            ));

        assertThat(mockFunction.value(arg(1)), sameInstance(RES_1));
        assertThat(mockFunction.value(arg(2)), sameInstance(RES_2));
        assertThat(mockFunction.value(arg(3)), sameInstance(RES_3));
    }


    @Test
    public void test_priCtorIterablePairs_differentArg_fail()
    {
        assertThat(() ->
            new MockFunction<>(
                new Seq<Pair<Matcher<Integer>, Object>>(
                    new ValuePair<>(equalTo(arg(1)), RES_1),
                    new ValuePair<>(equalTo(arg(2)), RES_2),
                    new ValuePair<>(equalTo(arg(3)), RES_3)
                ))
                .value(arg(5)), is(throwing(instanceOf(AssertionError.class))));
    }


    @Test
    public void test_secCtorIterablePairsVarargs_matchingArgs_pass()
    {
        MockFunction<Integer, Object> mockFunction = new MockFunction<>(
            new ValuePair<>(equalTo(arg(1)), RES_1),
            new ValuePair<>(equalTo(arg(2)), RES_2),
            new ValuePair<>(equalTo(arg(3)), RES_3)
        );

        assertThat(mockFunction.value(arg(1)), sameInstance(RES_1));
        assertThat(mockFunction.value(arg(2)), sameInstance(RES_2));
        assertThat(mockFunction.value(arg(3)), sameInstance(RES_3));
    }


    @Test
    public void test_secCtorIterableIterable_matchingArgs_pass()
    {
        Function<Integer, Object> mockFunction = new MockFunction<>(
            new Seq<>(equalTo(arg(1)), equalTo(arg(2)), equalTo(arg(3))),
            new Seq<>(RES_1, RES_2, RES_3));

        assertThat(mockFunction.value(arg(1)), sameInstance(RES_1));
        assertThat(mockFunction.value(arg(2)), sameInstance(RES_2));
        assertThat(mockFunction.value(arg(3)), sameInstance(RES_3));
    }


    @Test
    public void test_secCtorIterableIterable_differentArgs_fail()
    {
        assertThat(() ->
            new MockFunction<>(
                new Seq<>(equalTo(arg(1)), equalTo(arg(2)), equalTo(arg(3))),
                new Seq<>(RES_1, RES_2, RES_3))
                .value(arg(555)), is(throwing(instanceOf(AssertionError.class))));
    }


    @Test
    public void test_secCtorSingleMatcher_matchingArg_pass()
    {
        assertThat(new MockFunction<>(equalTo(arg(1)), RES_1).value(arg(1)), sameInstance(RES_1));
    }


    @Test
    public void test_ctorSingleMatcher_differentArg_fail()
    {
        assertThat(() ->
                new MockFunction<>(equalTo(arg(1)), RES_1).value(arg(2)),
            is(throwing(instanceOf(AssertionError.class))));
    }


    @Test
    public void test_secCtorSingle_sameInstanceArg_pass()
    {
        Integer arg = new Integer(1);
        assertThat(new MockFunction<>(arg, RES_1).value(arg), sameInstance(RES_1));
    }


    @Test
    public void test_ctorSingle_equalInstanceArg_fail()
    {
        assertThat(() ->
            new MockFunction<>(arg(1), RES_1).value(arg(1)), is(throwing(instanceOf(AssertionError.class))));
    }


    @Test
    public void test_exceptionMessage()
    {
        assertThat(() ->
            new MockFunction<>(arg(1), RES_1).value(arg(555)), is(throwing(instanceOf(AssertionError.class))));
    }


    // Shortcut for creating a value object:
    private Integer arg(int value)
    {
        return new Integer(value);
    }

}