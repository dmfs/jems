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

package org.dmfs.jems2.function;

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.function.SingleFunction.toSingle;
import static org.dmfs.jems2.hamcrest.matchers.function.FragileFunctionMatcher.associates;
import static org.dmfs.jems2.hamcrest.matchers.single.SingleMatcher.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Test {@link SingleFunction}.
 */
public class SingleFunctionTest
{
    @Test
    public void test()
    {
        assertThat(new SingleFunction<>(), associates("1", hasValue("1")));
        assertThat(toSingle(), associates("a", hasValue("a")));
    }
}