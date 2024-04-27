package org.acme.models;

public class Cnpj extends Document {
    public Cnpj(String numero) {
        super(numero);
    }

    @Override
    public boolean validate() {
        return true;
    }
}
