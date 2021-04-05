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

package org.dmfs.jems.stack.elementary;

import org.dmfs.jems.optional.Optional;
import org.dmfs.jems.stack.Stack;

import static org.dmfs.jems.optional.elementary.Absent.absent;


/**
 * A {@link Stack} without any elements.
 *
 * @param <Element>
 *         The type of the elements this {@link Stack} can hold.
 *
 * @author Marten Gajda
 */
@Deprecated
public final class EmptyStack<Element> implements Stack<Element>
{
    @Override
    public Optional<StackTop<Element>> top()
    {
        return absent();
    }
}
