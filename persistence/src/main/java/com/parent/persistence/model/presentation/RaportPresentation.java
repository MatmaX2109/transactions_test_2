package com.parent.persistence.model.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RaportPresentation {

    @JsonProperty("Tip tranzactie")
    private String type;

    @JsonIgnore
    private int count;

    @JsonProperty("Numar de tranzactii")
    private String countString;

    @JsonIgnore
    private BigDecimal total;

    @JsonProperty("Suma totala tranzactionata")
    private String sumaTotala;

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

    public String getCountString() {
        return countString;
    }

    public void setCountString(String countString) {
        this.countString = countString;
    }

    public String getSumaTotala() {
        return sumaTotala;
    }

    public void setSumaTotala(String sumaTotala) {
        this.sumaTotala = sumaTotala;
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
