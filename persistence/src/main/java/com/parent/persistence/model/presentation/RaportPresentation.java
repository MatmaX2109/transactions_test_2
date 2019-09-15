package com.parent.persistence.model.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class RaportPresentation {

    @JsonProperty("Tip tranzactie")
    private String type;

    @JsonProperty("Numar de tranzactii")
    private int count;

    @JsonProperty("Suma totala tranzactionata")
    private BigDecimal total;

    @JsonProperty("Detalii tranzactii")
    private List<TransactionDetails> details;

    public RaportPresentation(String type, int count, BigDecimal total, List<TransactionDetails> details) {
        this.type = type;
        this.count = count;
        this.total = total;
        this.details = details;
    }

    public RaportPresentation() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionDetails> getDetails() {
        return details;
    }

    public void setDetails(List<TransactionDetails> details) {
        this.details = details;
    }
}
