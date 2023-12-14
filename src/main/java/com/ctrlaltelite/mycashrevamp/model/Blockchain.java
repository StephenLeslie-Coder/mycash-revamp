package com.ctrlaltelite.mycashrevamp.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Blockchain {
    private List<Block> chain;
    private List<Transaction> mempool;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.mempool = new ArrayList<>();
        this.addGenesisBlock();
    }
    public void processPayment(Transaction paymentTransaction) {
        mempool.add(paymentTransaction);
    }

    public void minePendingPayments() {
        minePendingTransactions();
    }

    private void minePendingTransactions() {
        String previousHash = chain.isEmpty() ? "0" : chain.get(chain.size() - 1).getHash();
        long timestamp = System.currentTimeMillis();
        List<Transaction> transactions = new ArrayList<>(mempool);
        Block newBlock = new Block(previousHash, transactions, timestamp);
        newBlock.validateBlock(2);
        chain.add(newBlock);
        mempool.clear();
    }

    private void addGenesisBlock() {
        Block genesisBlock = new Block("0", new ArrayList<>(), System.currentTimeMillis());
        genesisBlock.validateBlock(2);
        chain.add(genesisBlock);
    }
}


