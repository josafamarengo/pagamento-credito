package org.acme.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.acme.api.dto.PaymentRequest;
import org.acme.common.enums.PersonType;

import java.util.regex.Pattern;

public class CpfCnpjValidator implements ConstraintValidator<ValidCpfCnpj, PaymentRequest> {

    private static final String CPF_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final String CNPJ_REGEX = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";

    @Override
    public boolean isValid(PaymentRequest paymentRequest, ConstraintValidatorContext context) {
        if (paymentRequest == null) {
            return true;
        }

        Integer personType = paymentRequest.personType();
        String cpfCnpj = paymentRequest.cpfCnpj();

        if (personType == null || cpfCnpj == null) {
            return true;
        }

        if (personType == PersonType.NATURAL_PERSON.getCode()) {
            return Pattern.matches(CPF_REGEX, cpfCnpj);
        } else if (personType == PersonType.LEGAL_PERSON.getCode()) {
            return Pattern.matches(CNPJ_REGEX, cpfCnpj);
        }

        return false;
    }
}