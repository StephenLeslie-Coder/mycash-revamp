package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.exceptions.GenericException;
import com.ctrlaltelite.mycashrevamp.model.Balance;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.service.TransactionService;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    TransactionService transactionService;
    @Override
    public Transaction initiateTransaction(String recipientAddress, String senderAddress, double amount, String currencyCode, KeyPair keyPair, List<Balance> balance) throws GenericException {

        Transaction transaction = new Transaction(senderAddress, recipientAddress, amount, currencyCode);
        String signature = transactionService.signTransaction(senderAddress,recipientAddress,amount,keyPair);
        transaction.setSignature(signature);

        List<Balance> foundBalance =  balance.stream().filter(x->x.getCurrencyCode().equals(currencyCode)).collect(Collectors.toList());

        if(foundBalance.isEmpty()){
            throw new GenericException("Balance not found",400);
        }
        if (!transactionService.isValidTransaction(transaction,foundBalance.get(0),keyPair)) {
            throw new GenericException("Invalid transaction. Check transaction details and balance.",400);
        }

        return transaction;
    }
}
