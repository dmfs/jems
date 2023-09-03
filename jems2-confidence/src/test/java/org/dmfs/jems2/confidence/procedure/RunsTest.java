package org.dmfs.jems2.confidence.procedure;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.Text;
import org.saynotobugs.confidence.description.Value;

import java.io.IOException;

import static org.saynotobugs.confidence.Assertion.assertThat;
import static org.saynotobugs.confidence.quality.Core.allOf;
import static org.saynotobugs.confidence.test.quality.Test.*;

class RunsTest
{
    @Test
    void testNoArgCtor()
    {
        assertThat(new Runs(),
            allOf(passes(() -> {}),
                fails(() -> {throw new IOException();}, "threw <java.io.IOException>"),
                hasDescription("")));
    }


    @Test
    void testPassDescriptionCtor()
    {
        assertThat(new Runs(new Text("abc")),
            allOf(passes(() -> {}),
                fails(() -> {throw new IOException();}, "threw <java.io.IOException>"),
                hasDescription("abc")));
    }


    @Test
    void testAllArgsCtor()
    {
        assertThat(new Runs(new Text("abc"), throwable -> new Spaced(new Text("xyz"), new Value(throwable))),
            allOf(passes(() -> {}),
                fails(() -> {throw new IOException();}, "xyz <java.io.IOException>"),
                hasDescription("abc")));
    }
}