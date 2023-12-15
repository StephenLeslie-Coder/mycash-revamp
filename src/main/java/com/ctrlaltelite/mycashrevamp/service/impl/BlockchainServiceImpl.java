package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.entity.Block;
import com.ctrlaltelite.mycashrevamp.model.Blockchain;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.repository.BlockRepository;
import com.ctrlaltelite.mycashrevamp.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainServiceImpl implements BlockchainService{
    private final Blockchain blockchain = new Blockchain();
    @Autowired
    private BlockRepository blockRepository;

    @Override
    public void processTransaction(Transaction transaction) {
            blockchain.processPayment(transaction);
    }

    @Override
    public Block mineBlock() {
        Block block = blockchain.minePendingPayments();
        return block;
    }

    @Override
    public Blockchain getBlockchain() {
        return blockchain;
    }
}
