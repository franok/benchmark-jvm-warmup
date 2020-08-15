package de.franok.pseudorandomnumbersorter.model;

public class EvenInteger {

    private final int value;

    public EvenInteger(int evenInt) {
        this.value = evenInt;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "EvenInteger{" +
                "value=" + value +
                '}';
    }
}
