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

package org.dmfs.iterators.composite;

import org.dmfs.iterators.EmptyIterator;
import org.dmfs.iterators.elementary.Seq;
import org.dmfs.jems.pair.Pair;
import org.dmfs.jems.pair.elementary.ValuePair;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.Iterator;

import static org.dmfs.iterators.composite.PairZippedTest.IteratorMatcher.contains;
import static org.dmfs.iterators.composite.PairZippedTest.PairMatcher.isPair;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Unit test for {@link PairZipped}.
 *
 * @author Gabor Keszthelyi
 */
public final class PairZippedTest
{

    @Test
    public void test()
    {
        assertThat(new PairZipped<>(EmptyIterator.<String>instance(), EmptyIterator.<String>instance()), IsEmptyIterator.<Pair<String, String>>emptyIterator());
        assertThat(new PairZipped<>(new Seq<>("1", "2", "3"), EmptyIterator.<String>instance()),
                IsEmptyIterator.<Pair<String, String>>emptyIterator());
        assertThat(new PairZipped<>(EmptyIterator.<String>instance(), new Seq<>("a", "b", "c")),
                IsEmptyIterator.<Pair<String, String>>emptyIterator());

        assertThat(new PairZipped<>(new Seq<>("1"), new Seq<>("a", "b", "c")), contains(isPair("1", "a")));
        assertThat(new PairZipped<>(new Seq<>("1", "2", "3"), new Seq<>("a")), contains(isPair("1", "a")));
        assertThat(new PairZipped<>(new Seq<>("1", "2", "3"), new Seq<>("a", "b", "c")),
                contains(isPair("1", "a"), isPair("2", "b"), isPair("3", "c")));
    }


    static final class IsEmptyIterator<E> extends TypeSafeMatcher<Iterator<E>>
    {

        @Override
        protected boolean matchesSafely(Iterator<E> item)
        {
            return !item.hasNext();
        }


        @Override
        public void describeTo(Description description)
        {

        }


        static <E> Matcher<java.util.Iterator<E>> emptyIterator()
        {
            return new IsEmptyIterator<>();
        }
    }


    static final class IteratorMatcher<E> extends TypeSafeMatcher<Iterator<E>>
    {
        private final Matcher<E>[] mExpected;


        private IteratorMatcher(Matcher<E>... expected)
        {
            mExpected = expected;
        }


        @Override
        protected boolean matchesSafely(final Iterator<E> actual)
        {
            return IsIterableContainingInOrder.contains(mExpected).matches(new Iterable<E>()
            {
                @Override
                public Iterator<E> iterator()
                {
                    return actual;
                }
            });
        }


        @Override
        public void describeTo(Description description)
        {

        }


        @SafeVarargs
        static <E> Matcher<Iterator<E>> contains(Matcher<E>... items)
        {
            return new IteratorMatcher<>(items);
        }
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