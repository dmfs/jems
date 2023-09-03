package org.dmfs.jems2.confidence.procedure;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.description.Spaced;
import org.saynotobugs.confidence.description.Text;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.grammar.SoIt;
import org.saynotobugs.confidence.quality.iterable.Contains;
import org.saynotobugs.confidence.quality.object.Throwing;
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
                new Spaced(new Text("adds <a> to list")), ArrayList::new, new SoIt<>(new Contains<>("a"))),
            new AllOf<>(
                new Passes<>(l -> l.add("a")),
                new Fails<>(l -> l.add("b"), "Procedure that adds <a> to list but [ \"b\" ] did not contain { \"a\" }\n  ..."),
                new Fails<>(l -> {
                    throw new IOException();
                }, "Procedure that adds <a> to list but [  ] did not contain { \"a\" } threw <java.io.IOException>"),
                new HasDescription("Procedure that adds <a> to list so it contains { \"a\" } after successful execution")
            ));
    }


    @Test
    void testWithoutDescription()
    {
        assertThat(new ProcedureThatAffects<>(ArrayList::new, new SoIt<>(new Contains<>("a"))),
            new AllOf<>(
                new Passes<>(l -> l.add("a")),
                new Fails<>(l -> l.add("b"), "Procedure that affects [  ] but [ \"b\" ] did not contain { \"a\" }\n  ..."),
                new Fails<>(l -> {
                    throw new IOException();
                }, "Procedure that affects [  ] but [  ] did not contain { \"a\" } threw <java.io.IOException>"),
                new HasDescription("Procedure that affects [  ] so it contains { \"a\" } after successful execution")
            ));
    }


    @Test
    void testThrowingException()
    {
        assertThat(new ProcedureThatAffects<>(
                new Text("adds <a> to list"),
                ArrayList::new,
                new SoIt<>(new Contains<>("a")),
                new Throwing(IllegalArgumentException.class)),
            new AllOf<>(
                new Passes<>(l -> {
                    l.add("a");
                    throw new IllegalArgumentException();
                }),
                new Fails<>(l -> l.add("a"), "Procedure that adds <a> to list ...\n  not throwing instance of <class java.lang.IllegalArgumentException>"),
                new Fails<>(l -> l.add("b"), "Procedure that adds <a> to list but [ \"b\" ] did not contain { \"a\" } not throwing instance of <class java.lang.IllegalArgumentException>"),
                new Fails<>(l -> {
                    l.add("a");
                    throw new IOException();
                }, "Procedure that adds <a> to list ...\n  throwing instance of <class java.io.IOException>"),
                new Fails<>(l -> {
                    throw new IOException();
                }, "Procedure that adds <a> to list but [  ] did not contain { \"a\" } throwing instance of <class java.io.IOException>"),
                new HasDescription("Procedure that adds <a> to list so it contains { \"a\" } throwing instance of <class java.lang.IllegalArgumentException>")
            ));
    }

}