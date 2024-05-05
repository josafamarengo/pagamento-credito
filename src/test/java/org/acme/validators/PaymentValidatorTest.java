package org.acme.validators;

import org.acme.models.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentValidatorTest {

    private PaymentValidator paymentValidator;

    @BeforeEach
    void setPaymentValidator() {
        paymentValidator = new PaymentValidator();
    }

    @Test
    void testValidatePlasticNumberValid() {
        Payment payment = Payment.builder().plasticNumber("1234 1234 1234 1234").build();
        assertDoesNotThrow(() -> paymentValidator.validatePlasticNumber(payment));
    }

    @Test
    void testValidatePlasticNumberInvalidCharacters() {
        Payment payment = Payment.builder().plasticNumber("123412341234abcd").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validatePlasticNumber(payment));
    }

    @Test
    void testValidadePlasticNumberMoreThan16Digits() {
        Payment payment = Payment.builder().plasticNumber("12341234123412345").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validatePlasticNumber(payment));
    }

    @Test
    void testValidatePlasticNumberLessThan16Digits() {
        Payment payment = Payment.builder().plasticNumber("123412341234123").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validatePlasticNumber(payment));
    }

    @Test
    void testValidateDocumentCpfValid() {
        Payment payment = Payment.builder().personType(1).cpfOrCnpj("123.123.123-12").build();
        assertDoesNotThrow(() -> paymentValidator.validateDocument(payment));
    }

    @Test
    void testValidateDocumentCnpjValid() {
        Payment payment = Payment.builder().personType(2).cpfOrCnpj("12.123.123/1234-12").build();
        assertDoesNotThrow(() -> paymentValidator.validateDocument(payment));
    }

    @Test
    void testValidateDocumentWrongPersonType() {
        Payment payment = Payment.builder().personType(2).cpfOrCnpj("123.123.123-12").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateDocument(payment));
    }

    @Test
    void testValidateDocumentInvalidFormatCpf() {
        Payment payment = Payment.builder().personType(1).cpfOrCnpj("1231.23.123-12").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateDocument(payment));
    }

    @Test
    void testValidateDocumentInvalidFormatCnpj() {
        Payment payment = Payment.builder().personType(2).cpfOrCnpj("12123123123412").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateDocument(payment));
    }

    @Test
    void testValidateExpirationDateValid() {
        int currentYear = LocalDate.now().getYear();
        Payment payment = Payment.builder().expirationMonth(12).expirationYear(currentYear).build();
        assertDoesNotThrow(() -> paymentValidator.validateExpirationDate(payment));
    }

    @Test
    void testValidateExpirationDateExpired() {
        int lastMonth = LocalDate.now().getMonthValue() - 1;
        int currentYear = LocalDate.now().getYear();

        Payment payment = Payment.builder().expirationMonth(lastMonth).expirationYear(currentYear).build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateExpirationDate(payment));
    }

    @Test
    void testValidateExpirationDateInvalidMonth() {
        Payment payment = Payment.builder().expirationMonth(13).expirationYear(2050).build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateExpirationDate(payment));
    }

    @Test
    void testValidateExpirationDateInvalidYear() {
        Payment payment = Payment.builder().expirationMonth(12).expirationYear(22222).build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateExpirationDate(payment));
    }

    @Test
    void testValidateCvvValid3Digits() {
        Payment payment =Payment.builder().cvv("123").build();
        assertDoesNotThrow(() -> paymentValidator.validateCvv(payment));
    }

    @Test
    void testValidateCvvValid4Digits() {
        Payment payment = Payment.builder().cvv("1234").build();
        assertDoesNotThrow(() -> paymentValidator.validateCvv(payment));
    }

    @Test
    void testValidateCvvInvalidCharacters() {
        Payment payment = Payment.builder().cvv("ABC").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateCvv(payment));
    }

    @Test
    void testValidateCvvMoreThan4Digits() {
        Payment payment = Payment.builder().cvv("12345").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateCvv(payment));
    }

    @Test
    void testValidateCvvLessThan3Digits() {
        Payment payment = Payment.builder().cvv("12").build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateCvv(payment));
    }

    @Test
    void testValidateAmountValid() {
        Payment payment = Payment.builder().amount(BigDecimal.valueOf(0.01)).build();
        assertDoesNotThrow(() -> paymentValidator.validateAmount(payment));
    }

    @Test
    void testValidateAmountIsZero() {
        Payment payment = Payment.builder().amount(BigDecimal.valueOf(0.00)).build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateAmount(payment));
    }

    @Test
    void testValidateAmountNegative() {
        Payment payment = Payment.builder().amount(BigDecimal.valueOf(-1.00)).build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateAmount(payment));
    }

    @Test
    void testValidateAmountInvalidFormat() {
        Payment payment = Payment.builder().amount(BigDecimal.valueOf(1.0)).build();
        assertThrows(IllegalArgumentException.class, () -> paymentValidator.validateAmount(payment));
    }
}