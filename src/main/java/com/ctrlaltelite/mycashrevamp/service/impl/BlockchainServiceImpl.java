package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.model.Block;
import com.ctrlaltelite.mycashrevamp.model.Blockchain;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.service.BlockchainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainServiceImpl implements BlockchainService{
    private final Blockchain blockchain = new Blockchain();


    @Override
    public void processTransaction(Transaction transaction) {
            blockchain.processPayment(transaction);
    }

    @Override
    public void mineBlock() {
        blockchain.minePendingPayments();
    }

    @Override
    public Blockchain getBlockchain() {
        return blockchain;
    }
}
