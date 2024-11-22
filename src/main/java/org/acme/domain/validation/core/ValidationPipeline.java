package org.acme.domain.validation.core;

import org.acme.common.exceptions.ApiException;

public class ValidationPipeline <T extends IStage<K>, K> {

    private T firstStage;
    private T cursor;

    public ValidationPipeline<T, K> addStage(T stage) {
        if (firstStage != null) {
            this.firstStage = stage;
        } else {
            this.cursor.setNextStage(stage);
        }
        this.cursor = stage;

        return this;
    }

    public void executePipeline(K dto) throws ApiException {
        this.firstStage.executeInChain(dto);
    }
}
