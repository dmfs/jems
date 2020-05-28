/*
 * Copyright 2020 dmfs GmbH
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

package org.dmfs.jems.hamcrest.matchers.mockito;

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.jems.iterable.elementary.Seq;
import org.dmfs.jems.procedure.Procedure;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;


/**
 * A Matcher for interactions with Mockito mocks.
 * <p>
 * Note that, by design this matcher mutates the mock.
 */
public final class MockInteractionMatcher<T> extends TypeSafeDiagnosingMatcher<T>
{
    private final Iterable<? extends Procedure<? super T>> mTestProcedures;


    public static <T> Matcher<T> notCalled()
    {
        return new MockInteractionMatcher<>(new EmptyIterable<>());
    }


    @SafeVarargs
    public static <T> Matcher<T> calledInOrder(Procedure<? super T>... verifications)
    {
        return new MockInteractionMatcher<T>(new Seq<>(verifications));
    }


    public MockInteractionMatcher(Iterable<? extends Procedure<? super T>> mTestProcedures)
    {
        this.mTestProcedures = mTestProcedures;
    }


    @Override
    protected boolean matchesSafely(T item, Description mismatchDescription)
    {
        InOrder inOrder = inOrder(item);
        try
        {
            for (Procedure<? super T> verification : mTestProcedures)
            {
                verification.process(inOrder.verify(item));
            }
            inOrder.verifyNoMoreInteractions();
        }
        catch (AssertionError e)
        {
            mismatchDescription.appendText(e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public void describeTo(Description description)
    {
        description.appendText("mock is called in order");
    }
}
