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

package org.dmfs.jems.hamcrest.matchers;

import org.dmfs.jems.fragile.Fragile;
import org.dmfs.jems.single.Single;
import org.hamcrest.Matcher;

import static org.dmfs.jems.hamcrest.matchers.LambdaMatcher.having;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * {@link Matcher} for {@link Single}.
 */
public final class SingleMatcher
{
    public static <T> Matcher<Fragile<? extends T, ? extends RuntimeException>> hasValue(Matcher<T> valueMatcher)
    {
        return having("value", Fragile::value, valueMatcher);
    }


    public static <T> Matcher<Fragile<? extends T, ? extends RuntimeException>> hasValue(T expectedValue)
    {
        return hasValue(equalTo(expectedValue));
    }
}
