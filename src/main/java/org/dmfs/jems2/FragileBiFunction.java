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

package org.dmfs.jems2;

/**
 * A binary function that can throw a checked {@link Exception}.
 *
 * @see BiFunction
 * @see FragileFunction
 */
@FunctionalInterface
public interface FragileBiFunction<Argument1, Argument2, Result, E extends Exception>
{
    /**
     * Returns the value of this function at the given arguments.
     */
    Result value(Argument1 argument1, Argument2 argument2) throws E;
}