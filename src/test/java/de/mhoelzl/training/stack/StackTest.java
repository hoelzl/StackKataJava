package de.mhoelzl.training.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack stack = new Stack();

    @Test
    void isEmpty_IsTrue_ForInitialStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void isEmpty_IsFalse_WhenElementWasPushedOnStack() {
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    void push_PlacesArgumentOnTopOfStack() {
        stack.push(7);

        assertEquals(7, stack.top());
    }

    @Test
    void push_PlacesSecondValueOnTop_WhenPushingTwoValues() {
        stack.push(7);
        stack.push(6);

        assertEquals(6, stack.top());
    }

    @Test
    void push_PlacesLastValueOnTop_WhenPushingMultipleValues() {
        stack.push(7);
        stack.push(6);
        stack.push(5);

        assertEquals(5, stack.top());
    }

    @Test
    void pop_RemovesTopElementAndReturnsIt_WhenStackIsNotEmpty() {
        stack.push(1);
        stack.push(2);

        int popResult = stack.pop();

        assertEquals(2, popResult);
        assertEquals(1, stack.top());
    }

    @Test
    void pop_ThrowsArrayIndexOutOfBoundsException_WhenStackIsEmpty() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.pop());
    }

    @Test
    void isEmpty_IsTrueAfterPushAndPop_WhenStackIsEmptyInitially() {
        stack.push(1);
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    void size_IsZero_WhenStackIsEmpty() {
        assertEquals(0, stack.size());
    }

    @Test
    void size_IsNumberOfElementsPushedOnStack() {
        int numElements = 10;
        for (int i = 0; i < numElements; i++) {
            stack.push(i);
        }
        assertEquals(numElements, stack.size());
    }

    @Test
    void count_IsZero_WhenStackIsEmpty() {
        assertEquals(0, stack.count(1));
    }

    @Test
    void count_IsZero_WhenElementIsNotOnStack() {
        stack.push(1);
        stack.push(2);
        stack.push(1);

        assertEquals(0, stack.count(3));
    }

    @Test
    void count_IsNumberOfOccurrencesOfNumberOnStack_WhenElementOccursOnStack() {
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
    void popDefault_RemovesAndReturnsTopElement_WhenStackIsNotEmpty() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.popDefault(3));
        assertEquals(1, stack.size());
        assertEquals(1, stack.top());
    }

    @Test
    void popDefault_ReturnsDefault_WhenStackIsEmpty() {
        assertEquals(3, stack.popDefault(3));
    }

    @Test
    void pop_ReturnsDefault_WhenStackIsEmptyAndSetDefaultWasCalled() {
        stack.setDefault(3);
        assertEquals(3, stack.pop());
    }

    @Test
    void pop_ReturnsAndRemovesTopElement_WhenStackIsNotEmptyAndSetDefaultWasCalled() {
        stack.setDefault(3);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(1, stack.top());
    }

    @Test
    void pop_ThrowsIndexOutOfBoundsException_WhenStackIsEmptyAndClearDefaultWasCalled() {
        stack.setDefault(3);
        stack.clearDefault();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.pop());
    }

    @Test
    void push_DoesNotRunOutOfMemoryAndPlacesLastValueOnTop_WhenPushingManyValues() {
        final int limit = 100000;
        for (int i = 0; i < limit; i++) {
            stack.push(i);
        }
        assertEquals(limit - 1, stack.top());
        assertEquals(limit, stack.size());
    }

    @Test
    void getCapacity_IsOne_WhenStackIsEmpty() {
        assertEquals(1, stack.getCapacity());
    }

    @Test
    void setCapacity_SetsCapacityWithoutModifyingStackContents_WhenArgumentIsLargerThanCurrentStackSize() {
        stack.push(10);
        stack.push(20);

        stack.setCapacity(64);

        assertEquals(64, stack.getCapacity());
        assertEquals(2, stack.size());
        assertEquals(20, stack.top());
    }

    @Test
    void setCapacity_SetsCapacityToStackSize_WhenArgumentIsSmallerThanCurrentStackSize() {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        stack.setCapacity(2);

        assertEquals(100, stack.getCapacity());
        assertEquals(100, stack.size());
        assertEquals(99, stack.top());
    }

    @Test
    void setCapacity_SetsCapacityToZero_WhenArgumentIsZeroAndStackIsEmpty() {
        stack.setCapacity(0);
        assertEquals(0, stack.getCapacity());
    }

    @Test
    void push_CanPushOnStack_WhenCapacityIsZero() {
        stack.setCapacity(0);
        stack.push(1);
        stack.push(2);

        assertTrue(stack.getCapacity() >= 2);
        assertEquals(2, stack.top());
    }
}
