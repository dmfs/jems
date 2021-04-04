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
 * A 1-tuple that may throw an {@link Exception} during retrieval of the value.
 * <p>
 * Fragiles are immutable and return an equal (also immutable) value for every invocation.
 *
 * @see Single
 */
public interface Fragile<T, E extends Exception>
{
    /**
     * Retrieve the value.
     */
    T value() throws E;
}
