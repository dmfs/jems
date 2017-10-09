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

package org.dmfs.iterables.composite;

import org.dmfs.iterables.ArrayIterable;
import org.dmfs.iterables.EmptyIterable;
import org.dmfs.jems.pair.Pair;
import org.dmfs.jems.pair.elementary.ValuePair;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

import static org.dmfs.iterables.composite.ZippedPairsTest.PairMatcher.isPair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterable;


/**
 * Unit test for {@link ZippedPairs}.
 *
 * @author Gabor Keszthelyi
 */
public final class ZippedPairsTest
{

    @Test
    public void testIterator()
    {
        assertThat(new ZippedPairs<>(EmptyIterable.<String>instance(), EmptyIterable.<String>instance()), emptyIterable());
        assertThat(new ZippedPairs<>(new ArrayIterable<>("1", "2", "3"), EmptyIterable.<String>instance()), emptyIterable());
        assertThat(new ZippedPairs<>(EmptyIterable.<String>instance(), new ArrayIterable<>("a", "b", "c")), emptyIterable());

        assertThat(new ZippedPairs<>(new ArrayIterable<>("1"), new ArrayIterable<>("a", "b", "c")), contains(isPair("1", "a")));
        assertThat(new ZippedPairs<>(new ArrayIterable<>("1", "2", "3"), new ArrayIterable<>("a")), contains(isPair("1", "a")));
        assertThat(new ZippedPairs<>(new ArrayIterable<>("1", "2", "3"), new ArrayIterable<>("a", "b", "c")),
                contains(isPair("1", "a"), isPair("2", "b"), isPair("3", "c")));
    }


    static final class PairMatcher<Left, Right> extends TypeSafeDiagnosingMatcher<Pair<Left, Right>>
    {
        private final Pair<Left, Right> mExpected;


        private PairMatcher(Pair<Left, Right> expected)
        {
            mExpected = expected;
        }


        @Override
        protected boolean matchesSafely(Pair<Left, Right> actual, Description mismatchDescription)
        {
            return actual.left().equals(mExpected.left()) && actual.right().equals(mExpected.right());
        }


        @Override
        public void describeTo(Description description)
        {
        }


        static <Left, Right> Matcher<Pair<Left, Right>> isPair(Left left, Right right)
        {
            return new PairMatcher<>(new ValuePair<Left, Right>(left, right));
        }

    }

}