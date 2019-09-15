package com.parent.validation.model;

import com.parent.validation.validators.CnpConstraint;
import com.parent.validation.validators.IbanConstraint;

import javax.validation.constraints.NotBlank;

public class TransactionMember {

    @NotBlank(message = "Numele este obligatoriu")
    private String name;

    @NotBlank(message = "CNP-ul este obligatoriu")
    @CnpConstraint
    private String cnp;

    @IbanConstraint
    private String iban;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
