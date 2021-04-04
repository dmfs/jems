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

package org.dmfs.jems2.stack;

import org.dmfs.jems2.Optional;
import org.dmfs.jems2.Single;
import org.dmfs.jems2.Stack;
import org.dmfs.jems2.single.Just;

import java.util.NoSuchElementException;


/**
 * A {@link Stack} of a single element.
 *
 * @param <Element>
 *     The type of the element on this {@link Stack}.
 */
public final class SingleStack<Element> implements Stack<Element>
{
    private final Optional<StackTop<Element>> mStackTop;


    public SingleStack(Element value)
    {
        this(new Just<>(value));
    }


    public SingleStack(Single<Element> value)
    {
        this(new PresentStackTop<>(value));
    }


    private SingleStack(Optional<StackTop<Element>> stackTop)
    {
        mStackTop = stackTop;
    }


    @Override
    public Optional<StackTop<Element>> top()
    {
        return mStackTop;
    }


    private final static class PresentStackTop<Element> implements Optional<StackTop<Element>>, StackTop<Element>
    {
        private final Single<Element> mElement;


        private PresentStackTop(Single<Element> element)
        {
            mElement = element;
        }


        @Override
        public boolean isPresent()
        {
            return true;
        }


        @Override
        public StackTop<Element> value() throws NoSuchElementException
        {
            return this;
        }


        @Override
        public Element element()
        {
            return mElement.value();
        }


        @Override
        public Stack<Element> bottom()
        {
            return new EmptyStack<>();
        }
    }
}
