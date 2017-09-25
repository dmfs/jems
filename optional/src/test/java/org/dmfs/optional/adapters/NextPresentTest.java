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

package org.dmfs.optional.adapters;

import org.dmfs.iterators.ArrayIterator;
import org.dmfs.optional.Absent;
import org.dmfs.optional.Optional;
import org.dmfs.optional.Present;
import org.dmfs.optional.hamcrest.AbsentMatcher;
import org.junit.Test;

import java.util.Iterator;

import static org.dmfs.optional.hamcrest.PresentMatcher.isPresent;
import static org.junit.Assert.assertThat;


/**
 * Unit test for {@link NextPresent}.
 *
 * @author Gabor Keszthelyi
 */
public final class NextPresentTest
{

    private static final Absent<String> ABSENT = Absent.absent();


    @Test
    public void testVariousCases()
    {
        assertThat(new NextPresent<>(new ArrayIterator<Optional<String>>()), AbsentMatcher.<String>isAbsent());

        assertThat(new NextPresent<>(new ArrayIterator<Optional<String>>(ABSENT)), AbsentMatcher.<String>isAbsent());
        assertThat(new NextPresent<>(new ArrayIterator<Optional<String>>(ABSENT, ABSENT)), AbsentMatcher.<String>isAbsent());

        assertThat(new NextPresent<>(new ArrayIterator<Optional<String>>(new Present<>("1"))), isPresent("1"));
        assertThat(new NextPresent<>(new ArrayIterator<Optional<String>>(ABSENT, new Present<>("1"))), isPresent("1"));
        assertThat(new NextPresent<>(new ArrayIterator<Optional<String>>(new Present<>("1"), ABSENT)), isPresent("1"));

        assertThat(new NextPresent<>(new ArrayIterator<Optional<String>>(new Present<>("1"), new Present<>("2"))), isPresent("1"));
    }


    @Test
    public void testUsedIterator()
    {
        Iterator<Optional<String>> it1 = new ArrayIterator<>(new Present<>("1"), ABSENT);
        it1.next();
        assertThat(new NextPresent<>(it1), AbsentMatcher.<String>isAbsent());

        Iterator<Optional<String>> it2 = new ArrayIterator<Optional<String>>(new Present<>("1"), new Present<>("2"));
        it2.next();
        assertThat(new NextPresent<>(it2), isPresent("2"));

        Iterator<Optional<String>> it3 = new ArrayIterator<Optional<String>>(new Present<>("1"), new Present<>("2"));
        it3.next();
        it3.next();
        assertThat(new NextPresent<>(it3), AbsentMatcher.<String>isAbsent());
    }

}