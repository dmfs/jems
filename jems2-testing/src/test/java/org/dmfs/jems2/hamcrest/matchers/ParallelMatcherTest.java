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

package org.dmfs.jems2.hamcrest.matchers;

import org.dmfs.jems2.Fragile;
import org.dmfs.jems2.Single;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.ParallelMatcher.parallel;
import static org.dmfs.jems2.hamcrest.matchers.matcher.MatcherMatcher.*;
import static org.dmfs.jems2.hamcrest.matchers.single.SingleMatcher.hasValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link ParallelMatcher}.
 */
public class ParallelMatcherTest
{
    @Test
    public void test()
    {
        assertThat(parallel(10, is(5)), describesAs("is <5> every time when run 10 times in parallel"));
        assertThat(parallel(10, is(5)), matches(5));
        assertThat(parallel(10, is(5)), mismatches(6, "at least one matcher failed"));
        assertThat(parallel(100, hasValue(5)), mismatches(new Single<Integer>()
        {
            int count = 0;


            @Override
            public synchronized Integer value()
            {
                // return wrong value after 90 invocations to simulate a race condition
                return count++ < 90 ? 5 : 6;
            }
        }, "at least one matcher failed"));

        assertThat(parallel(is(5)), describesAs("is <5> every time when run 1000 times in parallel"));
        assertThat(parallel(is(5)), matches(5));
        assertThat(parallel(is(5)), mismatches(6, "at least one matcher failed"));
        assertThat(parallel(hasValue(5)), mismatches(new Single<Integer>()
        {
            int count = 0;


            @Override
            public synchronized Integer value()
            {
                // return wrong value after 90 invocations to simulate a race condition
                return count++ < 90 ? 5 : 6;
            }
        }, "at least one matcher failed"));

        // interrupted tests fail
        assertThat(parallel(is(5)), mismatches(new Fragile<Integer, InterruptedException>()
        {
            int count = 0;


            @Override
            public synchronized Integer value() throws InterruptedException
            {
                if (count++ < 90)
                {
                    return 5;
                }
                throw new InterruptedException();
            }
        }, "at least one matcher failed"));

    }
}