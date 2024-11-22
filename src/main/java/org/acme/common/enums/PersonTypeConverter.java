package org.acme.common.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PersonTypeConverter implements AttributeConverter<PersonType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PersonType personType) {
        if (personType == null) {
            return null;
        }
        return personType.getCode();
    }

    @Override
    public PersonType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return PersonType.of(code);
    }
}
