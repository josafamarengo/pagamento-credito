package org.acme.validators;

import org.acme.models.Payment;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.regex.Pattern;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentValidator {

    public void validate(Payment payment) {
        validatePlasticNumber(payment);
        validateDocument(payment);
        validateExpirationDate(payment);
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

    private void validateDocument(Payment payment) {
        int personType = payment.getPersonType();

        if (personType == 1) {
            validateCpf(payment);
        } else if (personType == 2) {
            validateCnpj(payment);
        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido");
        }
    }

    private void validateCpf(Payment payment) {
        String cpf = payment.getCpfOrCnpj();
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

        if (!Pattern.matches(regex, cpf)) {
            throw new IllegalArgumentException("O CPF deve seguir o padrão XXX.XXX.XXX-XX");
        }
    }

    private void validateCnpj(Payment payment) {
        String cnpj = payment.getCpfOrCnpj();
        String regex = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";

        if(!Pattern.matches(regex, cnpj)) {
            throw new IllegalArgumentException("O CNPJ deve seguir o padrão XX.XXX.XXX/XXXX-XX");
        }
    }

    private void validateExpirationDate(Payment payment) {
        YearMonth currentYearMonth = YearMonth.now();

        // Obter o mês e o ano da data de vencimento
        YearMonth expirationYearMonth = YearMonth.of(payment.getExpirationYear(), payment.getExpirationMonth());

        // Comparar se a data de vencimento é posterior à data atual
        if(expirationYearMonth.isBefore(currentYearMonth)) {
            throw new IllegalArgumentException("A data de vencimento do cartão está expirada");
        }
    }

    private void validateCvv(Payment payment) {
        String cvv = payment.getCvv().trim();

        if (!cvv.matches("\\d{3,4}")) {
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
