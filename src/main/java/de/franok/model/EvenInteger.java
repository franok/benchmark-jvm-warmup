package de.franok.model;

public class EvenInteger {

    private final int value;

    public EvenInteger(int evenInt) {
        this.value = evenInt;
    }

    @Override
    public String toString() {
        return "EvenInteger{" +
                "value=" + value +
                '}';
    }
}
