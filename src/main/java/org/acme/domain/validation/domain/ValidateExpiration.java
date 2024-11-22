package org.acme.domain.validation.domain;

import org.acme.common.exceptions.ApiException;
import org.acme.common.exceptions.Errors;
import org.acme.domain.model.Payment;
import org.acme.domain.validation.core.ValidationStage;

public class ValidateExpiration extends ValidationStage<Payment> {
    @Override
    public void execute(Payment dto) throws ApiException {
        if (dto.isExpired()) throw new ApiException(Errors.CREDIT_CARD_EXPIRED.getMessage());
    }
}
