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

import org.dmfs.jems.function.Function;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;


/**
 * {@link Function} that creates a {@link Matcher} for the given argument that matches for object equality (equals()).
 * <p>
 * Note: This is a workaround for the lack of method references in Java 7.
 *
 * @author Gabor Keszthelyi
 */
public final class EqualMatcherFunction<T> implements Function<T, Matcher<T>>
{
    private static final Function INSTANCE = new EqualMatcherFunction<>();


    public static <T> Function<T, Matcher<T>> instance()
    {
        //noinspection unchecked
        return (Function<T, Matcher<T>>) INSTANCE;
    }


    @Override
    public Matcher<T> value(T target)
    {
        return CoreMatchers.equalTo(target);
    }
}
