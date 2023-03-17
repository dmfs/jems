package org.dmfs.jems2.confidence.optional;

import org.dmfs.jems2.Optional;
import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import java.util.NoSuchElementException;

import static org.dmfs.jems2.mockito.Mock.*;
import static org.saynotobugs.confidence.Assertion.assertThat;


class PresentTest
{

    @Test
    void testDefault()
    {
        assertThat(new Present<>(),
            new AllOf<>(
                new Passes<Optional<Object>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, returning(new Object())))
                ),
                new Fails<Optional<Object>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, throwing(new NoSuchElementException()))), "absent"),
                new Fails<Optional<Object>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, throwing(new NoSuchElementException()))), "threw <java.util.NoSuchElementException>"),
                new Fails<Optional<Object>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, throwing(new RuntimeException()))), "absent"),
                new Fails<Optional<Object>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, throwing(new RuntimeException()))), "threw <java.lang.RuntimeException>"),
                new HasDescription("present <anything>")));
    }


    @Test
    void testWithValue()
    {
        assertThat(new Present<>(123),
            new AllOf<>(
                new Passes<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, returning(123)))),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, returning(1234))), "present <1234>"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, throwing(new NoSuchElementException()))), "threw <java.util.NoSuchElementException>"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, throwing(new RuntimeException()))), "threw <java.lang.RuntimeException>"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, returning(123))), "absent"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, returning(1234))), "absent"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, throwing(new NoSuchElementException()))), "absent"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, throwing(new RuntimeException()))), "absent"),
                new HasDescription("present <123>")));
    }


    @Test
    void testWithMatcher()
    {
        assertThat(new Present<>(new EqualTo<>(123)),
            new AllOf<>(
                new Passes<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, returning(123)))),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, returning(1234))), "present <1234>"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, throwing(new NoSuchElementException()))), "threw <java.util.NoSuchElementException>"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(true)),
                        with(Optional::value, throwing(new RuntimeException()))), "threw <java.lang.RuntimeException>"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, returning(123))), "absent"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, returning(1234))), "absent"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, throwing(new NoSuchElementException()))), "absent"),
                new Fails<Optional<Integer>>(
                    mock(Optional.class,
                        with(Optional::isPresent, returning(false)),
                        with(Optional::value, throwing(new RuntimeException()))), "absent"),
                new HasDescription("present <123>")));
    }

}