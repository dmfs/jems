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

package org.dmfs.jems.predicate.composite;

import org.dmfs.jems.pair.elementary.ValuePair;
import org.dmfs.jems.predicate.elementary.SameAs;
import org.junit.Test;

import static org.dmfs.jems.mockito.doubles.TestDoubles.dummy;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Marten Gajda
 */
public class RightWithTest
{
    @Test
    public void testSatisfiedBy() throws Exception
    {
        Object rightDummy = dummy(Object.class);
        assertThat(new RightWith<>(new SameAs<>(rightDummy)).satisfiedBy(new ValuePair<>(dummy(Object.class), rightDummy)), is(true));
        assertThat(new RightWith<>(new SameAs<>(rightDummy)).satisfiedBy(new ValuePair<>(dummy(Object.class), dummy(Object.class))), is(false));
    }
}