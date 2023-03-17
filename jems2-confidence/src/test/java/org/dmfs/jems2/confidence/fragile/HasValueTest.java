package org.dmfs.jems2.confidence.fragile;

import org.dmfs.jems2.Fragile;
import org.dmfs.jems2.Single;
import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import java.io.IOException;

import static org.saynotobugs.confidence.Assertion.assertThat;


class HasValueTest
{
    @Test
    void testWithQuality()
    {
        assertThat(new HasValue<>(new EqualTo<>("123")),
            new AllOf<>(
                new Passes<>((Fragile<String, Exception>) () -> "123", (Single<String>) () -> "123"),
                new Fails<>(() -> "1234", "had value \"1234\""),
                new Fails<>(() -> {throw new IOException();}, "threw <java.io.IOException>"),
                new HasDescription("has value \"123\"")
            ));
    }


    @Test
    void testWithValue()
    {
        assertThat(new HasValue<>("123"),
            new AllOf<>(
                new Passes<>((Fragile<String, Exception>) () -> "123", (Single<String>) () -> "123"),
                new Fails<>(() -> "1234", "had value \"1234\""),
                new Fails<>(() -> {throw new IOException();}, "threw <java.io.IOException>"),
                new HasDescription("has value \"123\"")
            ));
    }
}