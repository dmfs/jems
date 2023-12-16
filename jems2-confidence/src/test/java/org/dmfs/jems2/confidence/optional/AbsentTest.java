package org.dmfs.jems2.confidence.optional;

import org.dmfs.jems2.Optional;
import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import java.util.NoSuchElementException;

import static org.dmfs.jems2.mockito.Mock.*;
import static org.saynotobugs.confidence.Assertion.assertThat;


class AbsentTest
{
    @Test
    void test()
    {
        assertThat(new Absent(),
            new AllOf<>(
                new Passes<Optional<String>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, throwing(new NoSuchElementException())))),
                new Fails<Optional<String>>(mock("optional with value", Optional.class,
                    with(Optional::isPresent, returning(true)),
                    with(Optional::value, throwing(new NoSuchElementException()))),
                    "<present but throwing <java.util.NoSuchElementException> >"),
                new Fails<Optional<String>>(mock("optional with value", Optional.class,
                    with(Optional::isPresent, returning(true)),
                    with(Optional::value, throwing(new RuntimeException()))),
                    "<present but throwing <java.lang.RuntimeException> >"),
                new Fails<Optional<String>>(mock("optional with value", Optional.class,
                    with(Optional::isPresent, returning(true)),
                    with(Optional::value, returning("123"))),
                    "<present \"123\" >"),
                new HasDescription("<absent>")
            ));
    }

}