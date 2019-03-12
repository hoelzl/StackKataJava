package de.mhoelzl.training.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack stack = new Stack();

    @Test
    void push_SomeValue() {
        stack.push(7);

        assertEquals(7, stack.top());
    }

    @Test
    void push_TwoValues() {
        stack.push(7);
        stack.push(6);

        assertEquals(6, stack.top());
    }

    @Test
    void push_MultipleValues() {
        stack.push(7);
        stack.push(6);
        stack.push(5);

        assertEquals(5, stack.top());
    }

    @Test
    void push_ManyValues() {
        final int limit = 10000;
        for (int i = 0; i < limit; i++) {
            stack.push(i);
        }
        assertEquals(limit - 1, stack.top());
    }

    @Test
    void isEmpty_InitialStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void isEmpty_isFalse_afterPush() {
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    void isEmpty_afterPushAndPop() {
        stack.push(1);
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    void pop_removesTopElement() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.top());
    }

    @Test
    void pop_throws_whenStackIsEmpty() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.pop());
    }

    @Test
    void size_isZero_whenStackIsEmpty() {
        assertEquals(0, stack.size());
    }

    @Test
    void size_isNumOfElementsOnStack() {
        int numElements = 10;
        for (int i = 0; i < numElements; i++) {
            stack.push(i);
        }
        assertEquals(numElements, stack.size());
    }

    @Test
    void count_isZeroForEmptyStack() {
        assertEquals(0, stack.count(1));
    }

    @Test
    void count_isNumOccurrencesOfNumberOnStack() {
        stack.push(1);
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(1);
        stack.push(1);
        stack.push(3);

        assertEquals(4, stack.count(1));
        assertEquals(1, stack.count(2));
        assertEquals(2, stack.count(3));
        assertEquals(0, stack.count(4));
    }

    @Test
    void popDefault_popsWhenStackIsNotEmpty() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.popDefault(3));
        assertEquals(1, stack.size());
        assertEquals(1, stack.top());
    }

    @Test
    void popDefault_returnsDefaultWhenStackIsEmpty() {
        assertEquals(3, stack.popDefault(3));
    }

    @Test
    void pop_returnsDefault_whenStackIsEmptyAndSetDefaultWasCalled() {
        stack.setDefault(3);
        assertEquals(3, stack.pop());
    }

    @Test
    void pop_pops_whenStackIsNotEmpty() {
        stack.setDefault(3);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(1, stack.top());
    }

    @Test
    void pop_throws_whenStackIsEmptyAndClearDefaultWasCalled() {
        stack.setDefault(3);
        stack.clearDefault();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.pop());
    }
}
