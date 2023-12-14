package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.exceptions.GenericException;
import com.ctrlaltelite.mycashrevamp.model.Balance;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.model.Wallet;

import java.security.KeyPair;
import java.util.List;

public interface WalletService {
    public Transaction initiateTransaction(String recipientAddress, String senderAddress, double amount, String currencyCode, KeyPair keyPair, List<Balance> balance) throws GenericException;

}
