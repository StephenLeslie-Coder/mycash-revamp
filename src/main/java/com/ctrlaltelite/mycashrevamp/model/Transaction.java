package com.ctrlaltelite.mycashrevamp.model;

import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import lombok.Data;

import java.security.KeyPair;
import java.time.LocalDate;
import java.util.List;

@Data
public class Transaction {
    private String senderAddress;
    private String recipientAddress;
    private double amount;
    private String currency;
    private LocalDate timestamp;
    private String signature;

    public Transaction(String senderAddress, String recipientAddress, double amount, String currency) {
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.amount = amount;
        this.currency = currency;
        timestamp = LocalDate.now();
    }

    public void signTransaction(KeyPair keyPair) {
        String data = senderAddress+ recipientAddress + amount;
        this.signature = CryptoUtils.sign(data, keyPair.getPrivate());
    }

    public boolean verifySignature(KeyPair keyPair) {
        String data = senderAddress + recipientAddress + amount;
        return CryptoUtils.verifySignature(data, this.signature, keyPair.getPublic());
    }

    public boolean isValidTransaction(Balance senderBalance, KeyPair keyPair) {

        return verifySignature(keyPair) && senderBalance.getAmount() >= amount && amount > 0;
    }
}
