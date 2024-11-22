package org.acme.common.consts;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PersonType {
    NATURAL_PERSON(1),
    LEGAL_PERSON(2);

    private final int code;

    PersonType(int code) {
        this.code = code;
    }

    public static PersonType of(int code) {
        return Arrays.stream(PersonType.values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Tipo de pessoa inválido: " + code));
    }
}
