package org.dmfs.jems2.confidence.predicate;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class SatisfiedByTest
{

    @Test
    void testMatch()
    {
        assertThat(new SatisfiedBy<>("12"),
            new AllOf<>(
                new Passes<>("12"::equals, s -> s.length() == 2),
                new Fails<>("123"::equals, "not satisfied by \"12\""),
                new HasDescription("satisfied by \"12\"")));
    }

}