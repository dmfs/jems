package org.dmfs.jems2.fragileprocedure;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.description.Text;

import java.io.IOException;

import static org.dmfs.jems2.confidence.Jems2.procedureThatAffects;
import static org.saynotobugs.confidence.Assertion.assertThat;
import static org.saynotobugs.confidence.core.quality.CharSequence.emptyCharSequence;
import static org.saynotobugs.confidence.core.quality.Grammar.is;
import static org.saynotobugs.confidence.core.quality.Grammar.soIt;
import static org.saynotobugs.confidence.core.quality.Object.hasToString;
import static org.saynotobugs.confidence.core.quality.Object.throwing;

class CompositeTest
{
    @Test
    void testNoDelegates()
    {
        assertThat(new Composite<>(),
            is(procedureThatAffects(StringBuilder::new,
                soIt(hasToString(emptyCharSequence())))));
    }

    @Test
    void test1Delegate()
    {
        assertThat(new Composite<>(sb -> sb.append("a")),
            is(procedureThatAffects(StringBuilder::new,
                soIt(hasToString("a")))));
    }


    @Test
    void test3Delegates()
    {
        assertThat(new Composite<>(sb -> sb.append("a"), sb -> sb.append("b"), sb -> sb.append("c")),
            is(procedureThatAffects(StringBuilder::new,
                soIt(hasToString("abc")))));
    }


    @Test
    void testThrow()
    {
        assertThat(new Composite<>(sb -> sb.append("a"), sb -> {throw new IOException();}, sb -> sb.append("c")),
            is(procedureThatAffects(new Text("stringbuilder"),
                StringBuilder::new,
                soIt(hasToString("a")),
                throwing(IOException.class))));
    }
}