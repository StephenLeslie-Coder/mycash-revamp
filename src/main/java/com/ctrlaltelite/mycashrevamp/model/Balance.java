package com.ctrlaltelite.mycashrevamp.model;

import lombok.Data;

@Data
public class Balance {
    private String name;
    private double amount;
    private boolean isCrypto;
    private String currencyCode;

}
