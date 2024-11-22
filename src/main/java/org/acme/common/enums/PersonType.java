package org.acme.common.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public enum PersonType {
    NATURAL_PERSON(1),
    LEGAL_PERSON(2);

    private static final Logger log = LoggerFactory.getLogger(PersonType.class);
    private final int code;

    PersonType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PersonType of(int code) {
        return Arrays.stream(PersonType.values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Tipo de pessoa inválido: " + code));
    }
}
