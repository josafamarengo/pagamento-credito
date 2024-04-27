package org.acme.models;

public class Cpf extends Document {
    public Cpf(String numero) {
        super(numero);
    }

    @Override
    public boolean validate() {
        return true;
    }
}
