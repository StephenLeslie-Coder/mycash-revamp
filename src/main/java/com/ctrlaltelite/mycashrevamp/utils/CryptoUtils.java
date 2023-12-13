package com.ctrlaltelite.mycashrevamp.utils;

import com.ctrlaltelite.mycashrevamp.model.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;

import static java.nio.charset.StandardCharsets.UTF_8;
import static sun.security.util.KnownOIDs.SHA256withRSA;

@Slf4j
public class CryptoUtils {
    private static final String ALGORITHM = "RSA";
    private static final String KEY_ALGORITHM = "DSA";
    public static String sign(String data, PrivateKey privateKey) {
        try {
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(privateKey);
            privateSignature.update(data.getBytes());

            byte[] signature = privateSignature.sign();

            return new String(Base64.getEncoder().encodeToString(signature).getBytes(), UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error signing the transaction: " + e.getMessage());
        }
    }

    public static boolean verifySignature(String data, String signature, PublicKey publicKey) {
        try {
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(data.getBytes());

            byte[] signatureBytes = Base64.getDecoder().decode(signature);

            return publicSignature.verify(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying the transaction signature: " + e.getMessage());
        }
    }



    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("SHA256withRSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKey(String privateKey) throws Exception {

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("SHA256withRSA");
        return keyFactory.generatePrivate(keySpec);


//        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
       // return keyFactory.generatePrivate(keySpec);
    }
    public static String calculateBlockHash(String previousHash, long timeStamp, int nonce, List<Transaction> data) {
        String dataToHash = previousHash
                + timeStamp
                + nonce
                + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            log.error(Level.SEVERE.toString(), ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }



}