package org.dmfs.jems2.iterator;

import org.junit.jupiter.api.Test;

import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.emptyIterator;
import static org.dmfs.jems2.hamcrest.matchers.iterator.IteratorMatcher.iteratorOf;
import static org.hamcrest.MatcherAssert.assertThat;

class WhileTest
{
    @Test
    void testEmpty()
    {
        assertThat(() -> new While<String>(s -> s.length() == 1, new EmptyIterator<>()),
            emptyIterator());
    }

    @Test
    void testMatchNever()
    {
        assertThat(() -> new While<>(s -> s.length() == 1, new Seq<>("ab", "cd", "ef")),
            emptyIterator());
    }


    @Test
    void testOneMatchingElement()
    {
        assertThat(() -> new While<>(s -> s.length() == 1, new Seq<>("a")),
            iteratorOf("a"));
    }

    @Test
    void testMatchMultiple()
    {
        assertThat(() -> new While<>(s -> s.length() == 1, new Seq<>("a", "b", "c")),
            iteratorOf("a", "b", "c"));
    }


    @Test
    void testOneMatch()
    {
        assertThat(() -> new While<>(s -> s.length() == 1, new Seq<>("a", "bc", "de", "fg", "h", "i")),
            iteratorOf("a"));
    }


    @Test
    void testSome()
    {
        assertThat(() -> new While<>(s -> s.length() == 1, new Seq<>("a", "b", "cd", "ef", "g", "h")),
            iteratorOf("a", "b"));
    }

}