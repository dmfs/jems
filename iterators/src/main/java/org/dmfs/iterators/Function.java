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

package org.dmfs.iterators;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @param <OriginalType>
 *         The type of the arguments.
 * @param <ResultType>
 *         The type of the results.
 *
 * @author Marten Gajda
 */
public interface Function<OriginalType, ResultType>
{
    /**
     * Applies this function to the given argument.
     *
     * @param argument
     *         The function argument.
     *
     * @return the function result,
     */
    ResultType apply(OriginalType argument);
}
