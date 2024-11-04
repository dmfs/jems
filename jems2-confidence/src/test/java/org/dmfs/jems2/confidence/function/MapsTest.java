package org.dmfs.jems2.confidence.function;

import org.dmfs.jems2.FragileFunction;
import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class MapsTest
{
    @Test
    void testWithValue()
    {
        assertThat(new Maps<>("abc", 3),
            new AllOf<>(
                new Passes<FragileFunction<String, Integer, ?>>(String::length, x -> 3),
                new Fails<FragileFunction<String, Integer, ?>>(x -> 4, "mapped \"abc\" 4"),
                new HasDescription("maps \"abc\" 3")
            ));
    }


    @Test
    void testWithQuality()
    {
        assertThat(new Maps<>("abc", new EqualTo<>(3)),
            new AllOf<>(
                new Passes<FragileFunction<String, Integer, ?>>(String::length, x -> 3),
                new Fails<FragileFunction<String, Integer, ?>>(x -> 4, "mapped \"abc\" 4"),
                new HasDescription("maps \"abc\" 3")
            ));
    }
}