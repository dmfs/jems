package org.dmfs.jems2.confidence.fragile;

import org.dmfs.jems2.Fragile;
import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.object.InstanceOf;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import java.io.IOException;

import static org.saynotobugs.confidence.Assertion.assertThat;


class ThrowingTest
{
    @Test
    void testWithQuality()
    {
        assertThat(new Throwing(new InstanceOf<>(IOException.class)),
            new AllOf<>(
                new Passes<>((Fragile<String, Exception>) () -> {throw new IOException("");}),
                new Fails<>(() -> "123", "had value() not throwing instance of <class java.io.IOException>"),
                new Fails<>(() -> {throw new NoSuchMethodException("");}, "had value() throwing instance of <class java.lang.NoSuchMethodException>"),
                new HasDescription("has value() throwing instance of <class java.io.IOException>")
            ));
    }


    @Test
    void testWithClass()
    {
        assertThat(new Throwing(IOException.class),
            new AllOf<>(
                new Passes<>((Fragile<String, Exception>) () -> {throw new IOException("");}),
                new Fails<>(() -> "123", "had value() not throwing instance of <class java.io.IOException>"),
                new Fails<>(() -> {throw new NoSuchMethodException("");}, "had value() throwing instance of <class java.lang.NoSuchMethodException>"),
                new HasDescription("has value() throwing instance of <class java.io.IOException>")
            ));
    }
}