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

import org.dmfs.iterables.EmptyIterable;
import org.dmfs.iterables.elementary.Seq;
import org.dmfs.jems.predicate.Predicate;
import org.dmfs.jems.predicate.elementary.Equals;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author marten
 */
public class AnyOfTest
{
    @Test
    public void testSatisfiedBy() throws Exception
    {
        // trivial predicate
        assertThat(new AnyOf<>().satisfiedBy(new Object()), is(true));
        assertThat(new AnyOf<>(EmptyIterable.<Predicate<Object>>instance()).satisfiedBy(new Object()), is(true));

        // test matching predicates
        assertThat(new AnyOf<>(new Equals<>("test"), new Equals<>("fail"), new Equals<>("fail")).satisfiedBy("test"), is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("test"))).satisfiedBy("test"), is(true));

        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("test"), new Equals<>("test"))).satisfiedBy("test"), is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("test"), new Equals<>("fail"))).satisfiedBy("test"), is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("fail"), new Equals<>("test"))).satisfiedBy("test"), is(true));

        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("test"), new Equals<>("test"), new Equals<>("test"))).satisfiedBy("test"),
                is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("test"), new Equals<>("test"), new Equals<>("fail"))).satisfiedBy("test"),
                is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("test"), new Equals<>("fail"), new Equals<>("test"))).satisfiedBy("test"),
                is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("fail"), new Equals<>("test"), new Equals<>("test"))).satisfiedBy("test"),
                is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("fail"), new Equals<>("test"), new Equals<>("fail"))).satisfiedBy("test"),
                is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("fail"), new Equals<>("fail"), new Equals<>("test"))).satisfiedBy("test"),
                is(true));
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("test"), new Equals<>("fail"), new Equals<>("fail"))).satisfiedBy("test"),
                is(true));

        // mismatched predicates

        // one delegate
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("fail"))).satisfiedBy("test"), is(false));

        // two delegates
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("fail"), new Equals<>("fail"))).satisfiedBy("test"), is(false));

        // three delegates
        assertThat(new AnyOf<>(new Seq<Predicate<String>>(new Equals<>("fail"), new Equals<>("fail"), new Equals<>("fail"))).satisfiedBy("test"),
                is(false));
    }

}