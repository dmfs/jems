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

package org.dmfs.jems2.hamcrest.matchers.fragile;

import org.dmfs.jems2.Fragile;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;


/**
 * A {@link Matcher} to match {@link Fragile}s which are supposed to be non-broken.
 */
public final class FragileMatcher<T> extends TypeSafeDiagnosingMatcher<Fragile<T, ?>>
{
    private final Matcher<? super T> mDelegate;


    public static <T> Matcher<Fragile<T, ?>> hasValue(Matcher<T> valueMatcher)
    {
        return new FragileMatcher<>(valueMatcher);
    }


    public static <T> Matcher<Fragile<T, ?>> hasValue(T expectedValue)
    {
        return new FragileMatcher<>(CoreMatchers.equalTo(expectedValue));
    }


    public FragileMatcher(Matcher<? super T> valueMatcher)
    {
        mDelegate = valueMatcher;
    }


    @Override
    protected boolean matchesSafely(Fragile<T, ?> item, Description mismatchDescription)
    {
        try
        {
            boolean result = mDelegate.matches(item.value());
            if (!result)
            {
                mismatchDescription.appendText("intact Fragile ");
                mDelegate.describeMismatch(item.value(), mismatchDescription);
            }
            return result;
        }
        catch (Throwable throwable)
        {
            mismatchDescription.appendText(String.format("broken throwing %s", throwable.getClass().getName()));
            return false;
        }
    }


    @Override
    public void describeTo(Description description)
    {
        description.appendText("intact Fragile ");
        mDelegate.describeTo(description);
    }
}
