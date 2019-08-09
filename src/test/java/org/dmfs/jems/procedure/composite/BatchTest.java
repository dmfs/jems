/*
 * Copyright 2019 dmfs GmbH
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

package org.dmfs.jems.procedure.composite;

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.iterables.SingletonIterable;
import org.dmfs.iterables.elementary.Seq;
import org.dmfs.jems.procedure.Procedure;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;


/**
 * Unit test for {@link Batch}.
 *
 * @author Marten Gajda
 */
public class BatchTest
{
    @Test
    public void testEmpty()
    {
        Procedure<Object> mock = mock(Procedure.class);
        new Batch<>(mock).process(EmptyIterable.instance());
        verifyZeroInteractions(mock);
    }


    @Test
    public void testSingle()
    {
        Procedure<Object> mock = mock(Procedure.class);
        Object contentMock = new Object();
        new Batch<>(mock).process(new SingletonIterable<>(contentMock));
        verify(mock).process(contentMock);
        verifyNoMoreInteractions(mock);
    }


    @Test
    public void testMultiple()
    {
        Procedure<Object> mock = mock(Procedure.class);
        Object contentMock1 = new Object();
        Object contentMock2 = new Object();
        Object contentMock3 = new Object();
        new Batch<>(mock).process(new Seq<>(contentMock1, contentMock2, contentMock3));
        verify(mock).process(contentMock1);
        verify(mock).process(contentMock2);
        verify(mock).process(contentMock3);
        verifyNoMoreInteractions(mock);
    }

}