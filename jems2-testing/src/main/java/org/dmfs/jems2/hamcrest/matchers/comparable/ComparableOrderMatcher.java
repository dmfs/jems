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

package org.dmfs.jems2.hamcrest.matchers.comparable;

import org.dmfs.jems2.iterable.Seq;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Comparator;


public final class ComparableOrderMatcher<T> extends TypeSafeDiagnosingMatcher<Comparator<T>>
{
    private final Iterable<? extends T> mOrderedValues;


    /**
     * Verifies that the {@link Comparator} imposes the given strict ordering on the given elements.
     */
    @SafeVarargs
    public static <T> Matcher<Comparator<T>> imposesOrderOf(T... orderedValues)
    {
        return new ComparableOrderMatcher<>(new Seq<>(orderedValues));
    }


    public ComparableOrderMatcher(Iterable<? extends T> orderedValues)
    {
        mOrderedValues = orderedValues;
    }


    @SuppressWarnings("CompareToZero")
    @Override
    protected boolean matchesSafely(Comparator<T> item, Description mismatchDescription)
    {
        int leftIdx = 0;
        for (T left : mOrderedValues)
        {

            int rightIdx = 0;
            for (T right : mOrderedValues)
            {
                if (leftIdx == rightIdx)
                {
                    if (item.compare(left, right) != 0)
                    {
                        mismatchDescription
                            .appendText("compared ")
                            .appendValue(left)
                            .appendText(" at index " + leftIdx)
                            .appendText(" to be non equal to itself (" + item.compare(left, right) + ")");
                        return false;
                    }
                }
                else if (leftIdx < rightIdx)
                {
                    if (item.compare(left, right) >= 0)
                    {
                        mismatchDescription
                            .appendText("compared elements ")
                            .appendValue(left)
                            .appendText(" at index " + leftIdx + " and ")
                            .appendValue(right)
                            .appendText(" at index " + rightIdx + " ")
                            .appendText(" in the wrong order (" + item.compare(left, right) + ")");
                        return false;
                    }
                }
                else // (leftIdx > rightIdx)
                {
                    if (item.compare(left, right) <= 0)
                    {
                        mismatchDescription
                            .appendText("compared elements ")
                            .appendValue(left)
                            .appendText(" at index " + leftIdx + " and ")
                            .appendValue(right)
                            .appendText(" at index " + rightIdx + " ")
                            .appendText(" in the wrong order (" + item.compare(left, right) + ")");
                        return false;
                    }
                }
                rightIdx++;
            }
            leftIdx++;
        }
        return true;
    }


    @Override
    public void describeTo(Description description)
    {
        description.appendValueList("imposes the following element order ", ",", "", mOrderedValues);
    }
}
