package com.parent.persistence.model.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Raport {

    @JsonProperty("Nume")
    private String name;

    @JsonProperty("CNP")
    private String cnp;

    @JsonProperty("IBAN")
    private String iban;

    @JsonProperty("Tranzactii")
    private List<RaportPresentation> transactions;

    public Raport(String name, String cnp, String iban, List<RaportPresentation> transactions) {
        this.name = name;
        this.cnp = cnp;
        this.iban = iban;
        this.transactions = transactions;
    }

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

    public List<RaportPresentation> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<RaportPresentation> transactions) {
        this.transactions = transactions;
    }
}
