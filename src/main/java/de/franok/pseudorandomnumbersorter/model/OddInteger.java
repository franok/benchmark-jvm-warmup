package de.franok.pseudorandomnumbersorter.model;

public class OddInteger {

    private final int value;

    public OddInteger(int oddInt) {
        this.value = oddInt;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "OddInteger{" +
                "value=" + value +
                '}';
    }
}
