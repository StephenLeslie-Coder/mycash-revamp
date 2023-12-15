package com.ctrlaltelite.mycashrevamp.bean;

import lombok.Data;


public class AddBalanceRequest {
    private String name;
    private double amount;
    private Boolean isCrypto;
    private String currencyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Boolean getIsCrypto() {
        return isCrypto;
    }

    public void setIsCrypto(Boolean crypto) {
        isCrypto = crypto;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
