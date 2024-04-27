package org.acme.factories;

import org.acme.commons.PersonType;
import org.acme.models.Cnpj;
import org.acme.models.Cpf;
import org.acme.models.Document;

public class DocumentFactory {
    public static Document criateDocument(PersonType personType, String number) {
        if (personType == PersonType.PESSOA_FISICA) {
            return new Cpf(number);
        } else if (personType == PersonType.PESSOA_JURIDICA) {
            return new Cnpj(number);
        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido");
        }
    }
}
