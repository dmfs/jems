package org.dmfs.jems2.optional;

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.optional.AbsentMatcher.absent;
import static org.dmfs.jems2.hamcrest.matchers.optional.PresentMatcher.present;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class IfTest
{

    @Test
    public void testBoolean()
    {
        assertThat(new If<>(true, new Absent<>()), is(absent()));
        assertThat(new If<>(false, new Absent<>()), is(absent()));
        assertThat(new If<>(true, new Present<>("abc")), is(present("abc")));
        assertThat(new If<>(false, new Present<>("abc")), is(absent()));
    }

    @Test
    public void testSingle()
    {
        assertThat(new If<>(() -> true, new Absent<>()), is(absent()));
        assertThat(new If<>(() -> false, new Absent<>()), is(absent()));
        assertThat(new If<>(() -> true, new Present<>("abc")), is(present("abc")));
        assertThat(new If<>(() -> false, new Present<>("abc")), is(absent()));
    }

}