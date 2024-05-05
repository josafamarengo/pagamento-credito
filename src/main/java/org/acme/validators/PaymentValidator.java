package org.acme.validators;

import org.acme.commons.Consts;
import org.acme.models.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.regex.Pattern;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Classe responsável por validar os dados de pagamento.
 *
 * Esta classe realiza a validação de diversos aspectos de um pagamento,
 * incluindo o número do cartão, o documento do titular, a data de
 * validade do cartão, o código de segurança (CVV) e o valor do pagamento.
 *
 * @author Josafá Braga Marengo
 * @version 1.0
 */
@ApplicationScoped
public class PaymentValidator {

    /**
     * Valida os dados de um pagamento.
     *
     * Este método chama os métodos de validação individuais para validar
     * cada aspecto do pagamento.
     *
     * @param payment O pagamento a ser validado.
     * @throws IllegalArgumentException Se algum dos dados do pagamento for inválido.
     */

    public void validate(Payment payment) {
        validatePlasticNumber(payment);
        validateDocument(payment);
        validateExpirationDate(payment);
        validateCvv(payment);
        validateAmount(payment);
    }

    /**
     * Valida o número do cartão de crédito.
     *
     * Este método remove espaços em branco do número do cartão,
     * verifica se contém apenas dígitos e se possui exatamente 16 dígitos.
     *
     * @param payment O objeto Payment contendo o número do cartão a ser validado.
     * @throws IllegalArgumentException Se o número do cartão contiver caracteres inválidos
     *                                  ou se não tiver exatamente 16 dígitos.
     */
    public void validatePlasticNumber(Payment payment) {
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

    /**
     * Valida o documento do titular do pagamento.
     *
     * Este método verifica o tipo de pessoa (física ou jurídica) com base
     * no tipo de pessoa fornecido no objeto Payment e realiza a validação apropriada(CPF ou CNPJ).
     *
     * @param payment O objeto Payment contendo o documento a ser validado.
     * @throws IllegalArgumentException Se o tipo de pessoa for inválido ou
     *                                  se o documento não estiver no formato adequado.
     */
    public void validateDocument(Payment payment) {
        int personType = payment.getPersonType();

        if (personType == 1) {
            validateCpf(payment);
        } else if (personType == 2) {
            validateCnpj(payment);
        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido");
        }
    }

    /**
     * Valida um CPF (Cadastro de Pessoa Física).
     *
     * Este método verifica se o CPF fornecido está no formato adequado XXX.XXX.XXX-XX.
     *
     * @param payment O objeto Payment contendo o CPF a ser validado.
     * @throws IllegalArgumentException Se o CPF não estiver no formato adequado.
     */
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

    /**
     * Valida a data de vencimento do cartão.
     *
     * Este método compara a data de vencimento fornecida no objeto Payment
     * com a data atual para garantir que o cartão não tenha expirado.
     *
     * @param payment O objeto Payment contendo a data de vencimento a ser validada.
     * @throws IllegalArgumentException Se a data de vencimento do cartão estiver expirada ou o formato dos dados estiver incorreto.
     */
    public void validateExpirationDate(Payment payment) {
        validateExpirationMonth(payment);
        validateExpirationYear(payment);

        YearMonth currentYearMonth = YearMonth.now();

        YearMonth expirationYearMonth = YearMonth.of(payment.getExpirationYear(), payment.getExpirationMonth());

        if(expirationYearMonth.isBefore(currentYearMonth)) {
            throw new IllegalArgumentException("A data de vencimento do cartão está expirada");
        }
    }

    private void validateExpirationMonth(Payment payment) {
        int month = payment.getExpirationMonth();
        if ( month > 12 || month < 1) throw new IllegalArgumentException("Mês Inválido");
    }

    private void validateExpirationYear(Payment payment) {
        int year = payment.getExpirationYear();
        int maxVal = LocalDate.now().getYear() + Consts.MAX_EXPIRATION_YEAR_OFFSET;

        if (year > maxVal || year < 1900) {
            throw new IllegalArgumentException("Formato do ano inválido");
        }
    }

    /**
     * Valida o código de segurança (CVV) do cartão.
     *
     * Este método verifica se o CVV fornecido está no formato correto,
     * que deve ter 3 ou 4 dígitos.
     *
     * @param payment O objeto Payment contnew Payment();
        payment.setPlasticNumber("12345678901234AB");endo o CVV a ser validado.
     * @throws IllegalArgumentException Se o CVV não estiver no formato correto.
     */
    public void validateCvv(Payment payment) {
        String cvv = payment.getCvv().trim();

        if (!cvv.matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV não está no formato correto.");
        }
    }

    /**
     * Valida o valor do pagamento.
     *
     * Este método verifica se o valor do pagamento é maior ou igual a 0.01 e se possui
     * exatamente duas casas decimais.
     *
     * @param payment O objeto Payment contendo o valor do pagamento a ser validado.
     * @throws IllegalArgumentException Se o valor do pagamento for menor do que 0.01
     *                                  ou se não tiver exatamente duas casas decimais.
     */
    public void validateAmount(Payment payment) {
        BigDecimal amount = payment.getAmount();

        if(amount.compareTo(new BigDecimal("0.01")) < 0 || amount.scale() != 2) {
            throw new IllegalArgumentException("O valor de pagamento deve ser maior ou igual a 0.01 e ter exatamente duas casas decimais.");
        }
    }

}
