package org.acme.models;

public abstract class Document {
    protected String number;

    public Document(String number) {
        this.number = number;
    }

    public abstract boolean validate();
}
