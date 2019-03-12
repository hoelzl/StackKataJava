package de.mhoelzl.training.stack;

import java.util.Arrays;

public class Stack {
    private int[] values = new int[1];
    private int currentIndex = -1;
    private int defaultValue;
    private boolean isDefaultEnabled;

    public void push(int value) {
        if (currentIndex >= values.length - 1) {
            int newLength = values.length == 0 ? 1 : values.length * 2;
            int[] newValues = Arrays.copyOf(values, newLength);
            values = newValues;
        }
        currentIndex++;
        values[currentIndex] = value;
    }

    public int top() {
        return values[currentIndex];
    }

    public boolean isEmpty() {
        return currentIndex == -1;
    }

    public int pop() {
        if (isDefaultEnabled && currentIndex == -1) {
            return defaultValue;
        }
        return values[currentIndex--];
    }

    public int size() {
        return currentIndex + 1;
    }

    public int count(int element) {
        int result = 0;
        for (int i = 0; i <= currentIndex; i++) {
            int elt = values[i];
            if (elt == element) {
                result++;
            }
        }
        return result;
    }

    public int popDefault(int defaultValue) {
        if (currentIndex == -1) {
            return defaultValue;
        }
        return pop();
    }

    public void setDefault(int newDefaultValue) {
        defaultValue = newDefaultValue;
        isDefaultEnabled = true;
    }

    public void clearDefault() {
        isDefaultEnabled = false;
    }

    public int getCapacity() {
        return values.length;
    }

    public void setCapacity(int newCapacity) {
        values = Arrays.copyOf(values, Math.max(currentIndex + 1, newCapacity));
    }
}
