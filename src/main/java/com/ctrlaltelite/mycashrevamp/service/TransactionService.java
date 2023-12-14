package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.model.Balance;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;

import java.security.KeyPair;

public interface TransactionService {

    public String signTransaction(String senderAddress, String recipientAddress, double amount, KeyPair keyPair);

    public boolean verifySignature(String senderAddress, String recipientAddress, double amount, KeyPair keyPair, String signature);

    public boolean isValidTransaction(Transaction transaction, Balance senderBalance, KeyPair keyPair);
}