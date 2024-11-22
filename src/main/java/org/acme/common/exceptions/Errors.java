package org.acme.common.exceptions;

public enum Errors {
    PAYMENT_NOT_FOUND("Não foi encontrado pagamento para o id: %s"),
    PERSISTENCE_EXCEPTION("Falha ao persistir os dados."),
    CREDIT_CARD_EXPIRED("O cartão de crédito está vencido.");

    final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ApiException getExceptionWithArgs(Object... args) {
        return new ApiException(String.format(message, args));
    }

}
