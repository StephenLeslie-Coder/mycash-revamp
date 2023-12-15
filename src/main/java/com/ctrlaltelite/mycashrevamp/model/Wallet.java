package com.ctrlaltelite.mycashrevamp.model;

import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
@NoArgsConstructor
public class Wallet {
    private String userId;
    private String userName;
    private KeyPair keyPair;
    private ArrayList<Balance> balance;
    private String address;

    public Wallet(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.balance = new ArrayList<Balance>();
        this.keyPair = this.generateKeyPair();
        this.address = this.keyPair.getPublic().toString();
    }


    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048,new SecureRandom());
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}