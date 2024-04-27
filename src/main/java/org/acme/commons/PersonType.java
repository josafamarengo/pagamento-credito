package org.acme.commons;

public enum PersonType {
    PESSOA_FISICA(1),
    PESSOA_JURIDICA(2);

    private final int value;

    PersonType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
