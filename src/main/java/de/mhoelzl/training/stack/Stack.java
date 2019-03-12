package de.mhoelzl.training.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List<Integer> values = new ArrayList<>();
    private int defaultValue;
    private boolean isDefaultEnabled;

    public void push(int value) {
        values.add(value);
    }

    public int top() {
        return values.get(values.size() - 1);
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public int pop() {
        if (isDefaultEnabled && values.size() == 0) {
            return defaultValue;
        }
        return values.remove(values.size() - 1);
    }

    public int size() {
        return values.size();
    }

    public int count(int element) {
        int result = 0;
        for (int elt : values) {
            if (elt == element) {
                result++;
            }
        }
        return result;
    }

    public int popDefault(int defaultValue) {
        if (values.isEmpty()) {
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
}
