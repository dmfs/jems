/*
 * Copyright 2018 dmfs GmbH
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

package org.dmfs.jems.hamcrest.matchers;

import org.dmfs.jems.fragile.Fragile;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.CoreMatchers.instanceOf;


/**
 * @author marten
 */
public final class FragileMatcher<T> extends TypeSafeDiagnosingMatcher<Fragile<T, ?>>
{
    private final Matcher<? super T> mDelegate;


    public FragileMatcher(Matcher<? super T> valueMatcher)
    {
        mDelegate = valueMatcher;
    }


    public static <T> Matcher<Fragile<T, ?>> hasValue(Matcher<T> valueMatcher)
    {
        return new FragileMatcher<>(valueMatcher);
    }


    public static <T> Matcher<Fragile<T, ?>> hasValue(T expectedValue)
    {
        return new FragileMatcher<>(CoreMatchers.equalTo(expectedValue));
    }


    public static <E extends Throwable> Matcher<Fragile<?, E>> isFragile(Class<E> exception)
    {
        return new FeatureMatcher<Fragile<?, E>, E>(instanceOf(exception), "Fragile throws", "Fragile throws")
        {
            @Override
            protected E featureValueOf(Fragile<?, E> actual)
            {
                try
                {
                    actual.value();
                    throw new AssertionError("Did not throw");
                }
                catch (Throwable e)
                {
                    return (E) e;
                }
            }
        };
    }


    @Override
    protected boolean matchesSafely(Fragile<T, ?> item, Description mismatchDescription)
    {
        try
        {
            boolean result = mDelegate.matches(item.value());
            if (!result)
            {
                mDelegate.describeMismatch(mDelegate.matches(item.value()), mismatchDescription);
            }
            return result;
        }
        catch (Throwable throwable)
        {
            mismatchDescription.appendText(String.format("did throw %s", throwable.getClass().getName()));
            return false;
        }
    }


    @Override
    public void describeTo(Description description)
    {
        mDelegate.describeTo(description);
    }
}
