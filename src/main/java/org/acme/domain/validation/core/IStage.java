package org.acme.domain.validation.core;

import org.acme.common.exceptions.ApiException;

public interface IStage<T> {
    void setNextStage(IStage<T> stage);
    void executeInChain(T dto) throws ApiException;
    void  execute(T dto) throws ApiException;
}
