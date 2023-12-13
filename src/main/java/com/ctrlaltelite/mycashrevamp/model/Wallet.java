package com.ctrlaltelite.mycashrevamp.model;

import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import lombok.Data;

import java.security.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
public class Wallet {
    private String userId;
    private String userName;
    private KeyPair keyPair;
    private double balance;
    private String address;

    public Wallet(String userId, String userName,double balance) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;

        this.keyPair = this.generateKeyPair();
        //this.address = CryptoUtils.generateAddress(this.keyPair.getPublic().toString());
        this.address = this.keyPair.getPublic().toString();
    }

    public Transaction createTransaction(String recipient, double amount) {

        Transaction transaction = new Transaction(this.getAddress(), recipient, amount, "JMD");
        transaction.signTransaction(getKeyPair());
        if (!transaction.isValidTransaction(balance,keyPair)) {
            throw new RuntimeException("Invalid transaction. Check transaction details and balance.");
        }

        return transaction;
    }

    private PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    private PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public  KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048,new SecureRandom());
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}