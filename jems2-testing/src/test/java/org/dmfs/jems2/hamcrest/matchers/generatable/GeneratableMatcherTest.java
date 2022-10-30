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

package org.dmfs.jems2.hamcrest.matchers.generatable;

import org.dmfs.jems2.Generatable;
import org.dmfs.jems2.Generator;
import org.dmfs.jems2.iterable.Seq;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.generatable.GeneratableMatcher.startsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.object.HasToString.hasToString;


/**
 * Test {@link GeneratableMatcher}.
 */
public final class GeneratableMatcherTest
{
    @Test
    public void testStartsWith()
    {
        assertThat(
            startsWith(1).matches(new TestGeneratable<>(1)),
            is(true));
        assertThat(
            startsWith(1, 2).matches(new TestGeneratable<>(1, 2)),
            is(true));
        assertThat(
            startsWith(1, 2, 3).matches(new TestGeneratable<>(1, 2, 3)),
            is(true));

        assertThat(
            startsWith(10).matches(new TestGeneratable<>(1)),
            is(false));
        assertThat(
            startsWith(10, 2, 3).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            startsWith(1, 20, 3).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            startsWith(1, 2, 30).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
    }


    @Test
    public void testStartsWith1()
    {
        assertThat(
            startsWith(new IsEqual<>(1)).matches(new TestGeneratable<>(1)),
            is(true));
        assertThat(
            startsWith(new IsEqual<>(1), new IsEqual<>(2)).matches(new TestGeneratable<>(1, 2)),
            is(true));
        assertThat(
            startsWith(new IsEqual<>(1), new IsEqual<>(2), new IsEqual<>(3)).matches(new TestGeneratable<>(1, 2, 3)),
            is(true));

        assertThat(
            startsWith(new IsEqual<>(10)).matches(new TestGeneratable<>(1)),
            is(false));
        assertThat(
            startsWith(new IsEqual<>(10), new IsEqual<>(2), new IsEqual<>(3)).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            startsWith(new IsEqual<>(1), new IsEqual<>(20), new IsEqual<>(3)).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            startsWith(new IsEqual<>(1), new IsEqual<>(2), new IsEqual<>(30)).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
    }


    @Test
    public void testStartsWith2()
    {
        assertThat(
            startsWith(new Seq<>(new IsEqual<>(1))).matches(new TestGeneratable<>(1)),
            is(true));
        assertThat(
            startsWith(new Seq<>(new IsEqual<>(1), new IsEqual<>(2))).matches(new TestGeneratable<>(1, 2)),
            is(true));
        assertThat(
            startsWith(new Seq<>(new IsEqual<>(1), new IsEqual<>(2), new IsEqual<>(3))).matches(new TestGeneratable<>(1, 2, 3)),
            is(true));

        assertThat(
            startsWith(new Seq<>(new IsEqual<>(10))).matches(new TestGeneratable<>(1)),
            is(false));
        assertThat(
            startsWith(new Seq<>(new IsEqual<>(10), new IsEqual<>(2), new IsEqual<>(3))).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            startsWith(new Seq<>(new IsEqual<>(1), new IsEqual<>(20), new IsEqual<>(3))).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            startsWith(new Seq<>(new IsEqual<>(1), new IsEqual<>(2), new IsEqual<>(30))).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
    }


    @Test
    public void testMatches()
    {
        assertThat(
            new GeneratableMatcher<>(new Seq<>(new IsEqual<>(1))).matches(new TestGeneratable<>(1)),
            is(true));
        assertThat(
            new GeneratableMatcher<>(new Seq<>(new IsEqual<>(1), new IsEqual<>(2))).matches(new TestGeneratable<>(1, 2)),
            is(true));
        assertThat(
            new GeneratableMatcher<>(new Seq<>(new IsEqual<>(1), new IsEqual<>(2), new IsEqual<>(3))).matches(new TestGeneratable<>(1, 2, 3)),
            is(true));
    }


    @Test
    public void testMismatches()
    {
        assertThat(
            new GeneratableMatcher<>(new Seq<>(new IsEqual<>(10))).matches(new TestGeneratable<>(1)),
            is(false));
        assertThat(
            new GeneratableMatcher<>(new Seq<>(new IsEqual<>(10), new IsEqual<>(2), new IsEqual<>(3))).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            new GeneratableMatcher<>(new Seq<>(new IsEqual<>(1), new IsEqual<>(20), new IsEqual<>(3))).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
        assertThat(
            new GeneratableMatcher<>(new Seq<>(new IsEqual<>(1), new IsEqual<>(2), new IsEqual<>(30))).matches(new TestGeneratable<>(1, 2, 3)),
            is(false));
    }


    @Test
    public void testMismatchDescription()
    {
        Description mismatchMsg = new StringDescription();
        new GeneratableMatcher<>(new Seq<>(new IsEqual<>(1), new IsEqual<>(20))).describeMismatch(new TestGeneratable<>(1, 2), mismatchMsg);
        assertThat(mismatchMsg.toString(), is("element at position 1 was <2>"));
    }


    @Test
    public void testDescribeTo()
    {
        Description description = new StringDescription();
        new GeneratableMatcher<>(new Seq<>(new IsEqual<>(1), new IsEqual<>(2))).describeTo(description);
        assertThat(description, hasToString("Generatable with start sequence <1>, <2>"));
    }


    /**
     * A Test Generatable which returns a few given values.
     */
    private final static class TestGeneratable<T> implements Generatable<T>
    {
        private final T[] mValues;


        @SafeVarargs
        private TestGeneratable(T... values)
        {
            mValues = values;
        }


        @Override
        public Generator<T> generator()
        {
            return new Generator<T>()
            {
                int i;


                @Override
                public T next()
                {
                    return mValues[i++];
                }
            };
        }
    }

}