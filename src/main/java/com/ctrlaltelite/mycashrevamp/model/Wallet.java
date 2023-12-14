package com.ctrlaltelite.mycashrevamp.model;

import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import lombok.Data;

import java.security.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
public class Wallet {
    private String userId;
    private String userName;
    private KeyPair keyPair;
    private List<Balance> balance;
    private String address;

    public Wallet(String userId, String userName,List<Balance> balance) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;

        this.keyPair = this.generateKeyPair();
        //this.address = CryptoUtils.generateAddress(this.keyPair.getPublic().toString());
        this.address = this.keyPair.getPublic().toString();
    }

    public Transaction createTransaction(String recipient, double amount,String currencyCode) {

        Transaction transaction = new Transaction(this.getAddress(), recipient, amount, currencyCode);
        transaction.signTransaction(getKeyPair());

        Balance foundBalance =  balance.stream().filter(x->x.getCurrencyCode().equals(currencyCode)).collect(Collectors.toList()).get(0);

        if(foundBalance==null){
            throw new RuntimeException("Invalid currency code");
        }
        if (!transaction.isValidTransaction(foundBalance,keyPair)) {
            throw new RuntimeException("Invalid transaction. Check transaction details and balance.");
        }

        return transaction;
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