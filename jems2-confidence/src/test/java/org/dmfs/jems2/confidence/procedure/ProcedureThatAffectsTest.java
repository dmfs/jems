package org.dmfs.jems2.confidence.procedure;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.grammar.SoIt;
import org.saynotobugs.confidence.quality.iterable.Contains;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import java.io.IOException;
import java.util.ArrayList;

import static org.saynotobugs.confidence.Assertion.assertThat;


class ProcedureThatAffectsTest
{

    @Test
    void testWithDescription()
    {
        assertThat(new ProcedureThatAffects<>(
                new Spaced(new TextDescription("adds <a> to list")), ArrayList::new, new SoIt<>(new Contains<>("a"))),
            new AllOf<>(
                new Passes<>(l -> l.add("a")),
                new Fails<>(l -> l.add("b"), "Procedure that adds <a> to list but [ \"b\" ] did not contain { \"a\" }"),
                new Fails<>(l -> {throw new IOException();}, "Procedure that threw <java.io.IOException> when called with [  ]"),
                new HasDescription("Procedure that adds <a> to list so it contains { \"a\" }")
            ));
    }


    @Test
    void testWithoutDescription()
    {
        assertThat(new ProcedureThatAffects<>(ArrayList::new, new SoIt<>(new Contains<>("a"))),
            new AllOf<>(
                new Passes<>(l -> l.add("a")),
                new Fails<>(l -> l.add("b"), "Procedure that affects [  ] but [ \"b\" ] did not contain { \"a\" }"),
                new Fails<>(l -> {throw new IOException();}, "Procedure that threw <java.io.IOException> when called with [  ]"),
                new HasDescription("Procedure that affects [  ] so it contains { \"a\" }")
            ));
    }

}