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

import org.dmfs.jems2.iterable.EmptyIterable;
import org.dmfs.jems2.iterable.Seq;
import org.junit.Test;

import static org.dmfs.jems2.hamcrest.matchers.predicate.PredicateMatcher.satisfiedBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;


/**
 * Test {@link AnyOf}.
 */
public class AnyOfTest
{
    @Test
    public void testSatisfiedBy()
    {
        // trivial predicate
        assertThat(new AnyOf<>(), is(satisfiedBy(new Object())));
        assertThat(new AnyOf<>(EmptyIterable.emptyIterable()), is(satisfiedBy(new Object())));

        // test matching predicates
        assertThat(new AnyOf<>(new Equals<>("test"), new Equals<>("fail"), new Equals<>("fail")), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("test"))), is(satisfiedBy("test")));

        assertThat(new AnyOf<>(new Seq<>(new Equals<>("test"), new Equals<>("test"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("test"), new Equals<>("fail"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("fail"), new Equals<>("test"))), is(satisfiedBy("test")));

        assertThat(new AnyOf<>(new Seq<>(new Equals<>("test"), new Equals<>("test"), new Equals<>("test"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("test"), new Equals<>("test"), new Equals<>("fail"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("test"), new Equals<>("fail"), new Equals<>("test"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("fail"), new Equals<>("test"), new Equals<>("test"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("fail"), new Equals<>("test"), new Equals<>("fail"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("fail"), new Equals<>("fail"), new Equals<>("test"))), is(satisfiedBy("test")));
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("test"), new Equals<>("fail"), new Equals<>("fail"))), is(satisfiedBy("test")));

        // mismatched predicates

        // one delegate
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("fail"))), is(not(satisfiedBy("test"))));

        // two delegates
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("fail"), new Equals<>("fail"))), is(not(satisfiedBy("test"))));

        // three delegates
        assertThat(new AnyOf<>(new Seq<>(new Equals<>("fail"), new Equals<>("fail"), new Equals<>("fail"))), is(not(satisfiedBy("test"))));
    }


    @Test
    public void testEqualsCtor()
    {
        assertThat(new AnyOf<>("a"), is(satisfiedBy("a")));
        assertThat(new AnyOf<>("a", "b", "c"), is(satisfiedBy("b")));

        assertThat(new AnyOf<>("a"), is(not(satisfiedBy("b"))));
        assertThat(new AnyOf<>("a", "b", "c"), is(not(satisfiedBy("d"))));
    }

}