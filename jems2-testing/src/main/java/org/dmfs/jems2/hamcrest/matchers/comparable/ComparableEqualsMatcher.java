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


public final class ComparableEqualsMatcher<T> extends TypeSafeDiagnosingMatcher<Comparator<T>>
{
    private final Iterable<? extends T> mEqualValues;


    /**
     * Verify that a {@link Comparator} determines all given values to be equal.
     */
    @SafeVarargs
    public static <T> Matcher<Comparator<T>> considersEqual(T... equalValues)
    {
        return new ComparableEqualsMatcher<>(new Seq<>(equalValues));
    }


    public ComparableEqualsMatcher(Iterable<? extends T> equalValues)
    {
        mEqualValues = equalValues;
    }


    @Override
    protected boolean matchesSafely(Comparator<T> item, Description mismatchDescription)
    {
        for (T left : mEqualValues)
        {
            for (T right : mEqualValues)
            {
                if (item.compare(left, right) != 0)
                {
                    mismatchDescription.appendValue(left)
                        .appendText(" and ")
                        .appendValue(right)
                        .appendText(" compared non-equal but to ")
                        .appendValue(item.compare(left, right));
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void describeTo(Description description)
    {
        description.appendValueList("considers the following elements equal: ", ",", "", mEqualValues);
    }
}
