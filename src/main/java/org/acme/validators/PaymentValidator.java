package org.acme.validators;

import org.acme.models.Payment;

import java.math.BigDecimal;

import org.acme.commons.Month;
import org.acme.commons.PersonType;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentValidator {

    public void validate(Payment payment) {
        validatePlasticNumber(payment);
        validatePersonType(payment);
        validateDocument(payment);
        validateExpirationMonth(payment);
        validateExpirationYear(payment);
        validateCvv(payment);
        validateAmount(payment);
    }

    private void validatePlasticNumber(Payment payment) {
        String plasticNumber = payment.getPlasticNumber()
                                .trim()
                                .replace(" ", "");

        if (!plasticNumber.matches("\\d+")) {
            throw new IllegalArgumentException("O número do cartão contém caracteres inválidos.");
        }

        if (plasticNumber.length() != 16) {
            throw new IllegalArgumentException("O número do cartão deve conter 16 dígitos.");
        }
    }

    private void validatePersonType(Payment payment) {
        PersonType personType = payment.getPersonType();
    
        if (personType == null) {
            throw new IllegalArgumentException("O tipo de pessoa não pode ser nulo.");
        }
    
        if (personType != PersonType.PESSOA_FISICA && personType != PersonType.PESSOA_JURIDICA) {
            throw new IllegalArgumentException("Tipo de pessoa inválido.");
        }
    }

    private void validateDocument(Payment payment) {
        if (payment.getPersonType() == PersonType.PESSOA_FISICA) {
            validateCpf(payment);
        }

        if (payment.getPersonType() == PersonType.PESSOA_JURIDICA) {
            validateCnpj(payment);
        }
    }

    private void validateCpf(Payment payment) {
        String cpf = payment.getCpfOrCnpj()
                                .trim()
                                .replace(" ", "")
                                .replace(".", "")
                                .replace("-", "");

            if (!cpf.matches("\\d+")) {
                throw new IllegalArgumentException("O cpf contém caracteres inválidos.");
            }

            if (cpf.length() != 11) {
                throw new IllegalArgumentException("O CPF deve conter 11 dígitos.");
            }
    }

    private void validateCnpj(Payment payment) {
        String cnpj = payment.getCpfOrCnpj()
                                .trim()
                                .replace(" ", "")
                                .replace(".", "")
                                .replace("-", "")
                                .replace("/", "");

            if (!cnpj.matches("\\d+")) {
                throw new IllegalArgumentException("O CNPJ contém caracteres inválidos.");
            }

            if (cnpj.length() != 14) {
                throw new IllegalArgumentException("O CNPJ deve conter 11 dígitos.");
            }
    }

    private void validateExpirationMonth(Payment payment) {
        Month expirationMonth = payment.getExpirationMonth();

        if (expirationMonth == null) {
            throw new IllegalArgumentException("O mês de vencimento não pode ser nulo.");
        }
    }

    private void validateExpirationYear(Payment payment) {
        String expirationYear = payment.getExpirationYear().trim();
        if (!expirationYear.matches("\\d+") || expirationYear.length() != 4) {
            throw new IllegalArgumentException("O ano do vencimento não está no formato correto: 'AAAA'.");
        }
    }

    private void validateCvv(Payment payment) {
        String cvv = payment.getCvv().trim();

        if (!cvv.matches("\\d+") || cvv.length() > 4 || cvv.length() < 3) {
            throw new IllegalArgumentException("CVV não está no formato correto.");
        }
    }

    private void validateAmount(Payment payment) {
        BigDecimal amount = payment.getAmount();

        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor de pagamento não pode ser menor do que ZERO.");
        }
    }
}
