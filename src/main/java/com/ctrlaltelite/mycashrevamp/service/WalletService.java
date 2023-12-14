package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.exception.GenericException;
import com.ctrlaltelite.mycashrevamp.model.Balance;
import com.ctrlaltelite.mycashrevamp.model.Transaction;

import java.security.KeyPair;
import java.util.List;
import java.util.stream.Collectors;

public interface WalletService {
    public Transaction initiateTransaction(String recipientAddress, String senderAddress, double amount, String currencyCode, KeyPair keyPair, List<Balance> balance) throws GenericException;


}
