package org.dmfs.jems.predicate.composite;

import org.dmfs.jems.predicate.Predicate;
import org.junit.Test;

import static org.dmfs.jems.mockito.doubles.TestDoubles.failingMock;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;


/**
 * @author marten
 */
public class NotTest
{
    @Test
    public void testSatisfiedBy() throws Exception
    {
        Predicate<String> mockPredicate = failingMock(Predicate.class);
        doReturn(true).when(mockPredicate).satisfiedBy("match");
        doReturn(false).when(mockPredicate).satisfiedBy("mismatch");

        assertThat(new Not<>(mockPredicate).satisfiedBy("match"), is(false));
        assertThat(new Not<>(mockPredicate).satisfiedBy("mismatch"), is(true));
    }
}