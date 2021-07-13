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
 * A special function which returns true whenever the argument matches this predicate.
 * <p>
 * Predicates must be immutable.
 */
@FunctionalInterface
public interface Predicate<T>
{
    /**
     * Returns whether the given instance satisfies this predicate.
     */
    boolean satisfiedBy(T testedInstance);
}
