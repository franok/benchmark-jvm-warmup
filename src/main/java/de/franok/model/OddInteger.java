package de.franok.model;

public class OddInteger {

    private final int value;

    public OddInteger(int oddInt) {
        this.value = oddInt;
    }

    @Override
    public String toString() {
        return "OddInteger{" +
                "value=" + value +
                '}';
    }
}
