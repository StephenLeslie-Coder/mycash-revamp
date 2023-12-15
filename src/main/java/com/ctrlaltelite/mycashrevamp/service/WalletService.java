package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.bean.GenericResponse;
import com.ctrlaltelite.mycashrevamp.entity.User;
import com.ctrlaltelite.mycashrevamp.entity.Wallet;
import com.ctrlaltelite.mycashrevamp.exceptions.GenericException;
import com.ctrlaltelite.mycashrevamp.model.Balance;
import com.ctrlaltelite.mycashrevamp.model.Transaction;

import java.security.KeyPair;
import java.util.List;

public interface WalletService {
    public Transaction initiateTransaction(String recipientAddress, String senderAddress, double amount, String currencyCode, KeyPair keyPair, List<Balance> balance) throws GenericException;

    Wallet createWallet(User user);
    public GenericResponse addBalanceToWallet(String name, double amount, boolean isCrypto, String currencyCode, String username) throws GenericException;

    public void adjustBalance(double amount, String currencyCode, String username) throws GenericException;

}
