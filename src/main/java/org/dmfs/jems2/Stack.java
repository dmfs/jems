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
 * An immutable stack. By design elements can only be retrieved from the top.
 */
public interface Stack<Element>
{
    /**
     * Retrieves the top of this Stack. If the stack is empty the {@link StackTop} will be absent.
     */
    Optional<StackTop<Element>> top();

    /**
     * The top of a {@link Stack}.
     */
    interface StackTop<Element>
    {
        /**
         * Returns the topmost stack element.
         */
        Element element();

        /**
         * Returns the underlying {@link Stack}.
         */
        Stack<Element> bottom();
    }
}
