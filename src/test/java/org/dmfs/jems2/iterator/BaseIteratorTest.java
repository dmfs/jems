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

package org.dmfs.jems2.iterator;

import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.LambdaMatcher.having;
import static org.dmfs.jems2.hamcrest.matchers.fragile.BrokenFragileMatcher.throwing;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
 * Test methods of {@link BaseIterator}.
 */
public class BaseIteratorTest
{
    @Test
    public void test()
    {
        assertThat(new BaseIterator()
                   {
                       @Override
                       public boolean hasNext()
                       {
                           fail();
                           return false;
                       }


                       @Override
                       public Object next()
                       {
                           fail();
                           return null;
                       }
                   },
            allOf(
                having(i -> () -> {
                    i.remove();
                    return null;
                }, is(throwing(UnsupportedOperationException.class))),
                having(i -> i::hashCode, is(throwing(UnsupportedOperationException.class))),
                having(i -> () -> i.equals(new Object()), is(throwing(UnsupportedOperationException.class)))
            ));
    }
}