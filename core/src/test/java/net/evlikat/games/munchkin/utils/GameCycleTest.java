package net.evlikat.games.munchkin.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GameCycleTest {

    private GameCycle cycle;

    @Mock
    private BooleanSupplier s1;
    @Mock
    private BooleanSupplier s2;
    @Mock
    private BooleanSupplier s3;

    @Before
    public void setUp() throws Exception {
        cycle = new GameCycle(Arrays.asList(s1, s2, s3));
    }

    @Test
    public void shouldIterateFalse() {
        when(s1.getAsBoolean()).thenReturn(false);
        when(s2.getAsBoolean()).thenReturn(false);
        when(s3.getAsBoolean()).thenReturn(false);
        cycle.run();
        InOrder order = Mockito.inOrder(s1, s2, s3);
        order.verify(s1).getAsBoolean();
        order.verify(s2).getAsBoolean();
        order.verify(s3).getAsBoolean();
        verifyNoMoreInteractions(s1, s2, s3);
    }

    @Test
    public void shouldIterateAgainWhenTrue() {
        when(s1.getAsBoolean()).thenReturn(false).thenReturn(false);
        when(s2.getAsBoolean()).thenReturn(true).thenReturn(false);
        when(s3.getAsBoolean()).thenReturn(false);
        cycle.run();
        InOrder order = Mockito.inOrder(s1, s2, s3);
        order.verify(s1).getAsBoolean();
        order.verify(s2).getAsBoolean();
        order.verify(s3).getAsBoolean();
        order.verify(s1).getAsBoolean();
        order.verify(s2).getAsBoolean();
        order.verifyNoMoreInteractions();
    }

    @Test
    public void shouldIterateAgainWhenLastTrue() {
        when(s1.getAsBoolean()).thenReturn(false).thenReturn(false);
        when(s2.getAsBoolean()).thenReturn(false).thenReturn(false);
        when(s3.getAsBoolean()).thenReturn(true).thenReturn(false);
        cycle.run();
        InOrder order = Mockito.inOrder(s1, s2, s3);
        order.verify(s1).getAsBoolean();
        order.verify(s2).getAsBoolean();
        order.verify(s3).getAsBoolean();
        order.verify(s1).getAsBoolean();
        order.verify(s2).getAsBoolean();
        order.verify(s3).getAsBoolean();
        order.verifyNoMoreInteractions();
    }
}