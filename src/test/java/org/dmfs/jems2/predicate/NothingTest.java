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

package org.dmfs.jems2.predicate;

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.predicate.PredicateMatcher.satisfiedBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;


/**
 * Test {@link Nothing}.
 */
public class NothingTest
{
    @Test
    public void testSatisfiedBy()
    {
        assertThat(new Nothing<>(), is(not(satisfiedBy("test"))));
        assertThat(new Nothing<>(), is(not(satisfiedBy(1))));
        assertThat(new Nothing<>(), is(not(satisfiedBy(true))));
        assertThat(new Nothing<>(), is(not(satisfiedBy(new Object()))));
    }

}