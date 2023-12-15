package com.ctrlaltelite.mycashrevamp.model;


import com.ctrlaltelite.mycashrevamp.repository.BlockRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Data
public class Blockchain {
    private List<Block> chain;
    private List<Transaction> mempool;

    @Autowired
    BlockRepository blockRepository;

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


        com.ctrlaltelite.mycashrevamp.entity.Block block = new com.ctrlaltelite.mycashrevamp.entity.Block();
        block.setHash(newBlock.getHash());
        block.setPreviousHash(newBlock.getPreviousHash());
        block.setTransactions(cloneBean(newBlock.getTransactions()));
        block.setNonce(newBlock.getNonce());
        block.setTimestamp(newBlock.getTimestamp());
        blockRepository.save(block);

        mempool.clear();
    }

    private void addGenesisBlock() {
        Block genesisBlock = new Block("0", new ArrayList<>(), System.currentTimeMillis());
        genesisBlock.validateBlock(2);
        chain.add(genesisBlock);
    }

    private List<com.ctrlaltelite.mycashrevamp.entity.Transaction> cloneBean(List<Transaction> transactions){
        List<com.ctrlaltelite.mycashrevamp.entity.Transaction> cloneTransactions = new ArrayList<>();

        for(Transaction transaction: transactions){
            com.ctrlaltelite.mycashrevamp.entity.Transaction clone  = new com.ctrlaltelite.mycashrevamp.entity.Transaction();
            clone.setAmount(transaction.getAmount());
            clone.setReceiver_address(transaction.getRecipientAddress());
            clone.setTimestamp(transaction.getTimestamp());
            clone.setTimestamp(transaction.getTimestamp());
            clone.setTransaction_hash(transaction.getSignature());
            clone.setCurrency(transaction.getCurrency());
            cloneTransactions.add(clone);
        }

        return cloneTransactions;
    };
}


