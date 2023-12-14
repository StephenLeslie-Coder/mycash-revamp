package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.model.Balance;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.service.TransactionService;
import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public String signTransaction(String senderAddress,String recipientAddress,double amount, KeyPair keyPair) {
        String data = senderAddress+ recipientAddress + amount;
        return CryptoUtils.sign(data, keyPair.getPrivate());

    }

    @Override
    public boolean verifySignature(String senderAddress,String recipientAddress,double amount, KeyPair keyPair,String signature) {
        String data = senderAddress + recipientAddress + amount;
        return CryptoUtils.verifySignature(data, signature, keyPair.getPublic());
    }

    @Override
    public boolean isValidTransaction(Transaction transaction, Balance senderBalance, KeyPair keyPair) {
        return verifySignature(transaction.getSenderAddress(),transaction.getRecipientAddress(),transaction.getAmount(),keyPair,transaction.getSignature()) && senderBalance.getAmount() >= transaction.getAmount() && transaction.getAmount() > 0;
    }
}
