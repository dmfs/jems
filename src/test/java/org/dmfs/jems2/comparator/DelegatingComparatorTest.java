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

package org.dmfs.jems2.comparator;

import org.junit.Test;

import java.util.Comparator;

import static org.dmfs.jems2.hamcrest.matchers.LambdaMatcher.having;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


/**
 * Test {@link DelegatingComparator}.
 */
public class DelegatingComparatorTest
{
    @Test
    public void test()
    {
        assertThat(new DelegatingComparator<Integer>(Comparator.naturalOrder())
                   {
                   },
            having(c -> c.compare(1, 3), lessThan(0)));

        assertThat(new DelegatingComparator<Integer>(Comparator.naturalOrder())
                   {
                   },
            having(c -> c.compare(3, 3), equalTo(0)));

        assertThat(new DelegatingComparator<Integer>(Comparator.naturalOrder())
                   {
                   },
            having(c -> c.compare(3, 1), greaterThan(0)));
    }
}