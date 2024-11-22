package org.acme.domain.validation.core;

import org.acme.common.exceptions.ApiException;

public abstract class ValidationStage<T> implements IStage<T> {
    protected IStage<T> nextStage;

    @Override
    public void setNextStage(IStage<T> stage) {
        this.nextStage = stage;
    }

    @Override
    public void executeInChain(T dto) throws ApiException {
        this.execute(dto);
        if(this.nextStage != null) {
            this.nextStage.executeInChain(dto);
        }
    }
}
