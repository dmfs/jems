package org.dmfs.jems2.iterable;

import org.junit.jupiter.api.Test;

import static org.saynotobugs.confidence.Assertion.assertThat;
import static org.saynotobugs.confidence.quality.Core.emptyIterable;
import static org.saynotobugs.confidence.quality.Core.iterates;

class WhileTest
{
    @Test
    void testEmpty()
    {
        assertThat(new While<>(s -> s.length() == 1, new EmptyIterable<String>()),
            emptyIterable());
    }

    @Test
    void testMatchNever()
    {
        assertThat(new While<>(s -> s.length() == 1, new Seq<>("ab", "cd", "ef")),
            emptyIterable());
    }


    @Test
    void testOneMatchingElement()
    {
        assertThat(new While<>(s -> s.length() == 1, new Seq<>("a")),
            iterates("a"));
    }

    @Test
    void testMatchMultiple()
    {
        assertThat(new While<>(s -> s.length() == 1, new Seq<>("a", "b", "c")),
            iterates("a", "b", "c"));
    }


    @Test
    void testOneMatch()
    {
        assertThat(new While<>(s -> s.length() == 1, new Seq<>("a", "bc", "de", "fg", "h", "i")),
            iterates("a"));
    }


    @Test
    void testSome()
    {
        assertThat(new While<>(s -> s.length() == 1, new Seq<>("a", "b", "cd", "ef", "g", "h")),
            iterates("a", "b"));
    }

}