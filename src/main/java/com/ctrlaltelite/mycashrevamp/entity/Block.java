package com.ctrlaltelite.mycashrevamp.entity;

import com.ctrlaltelite.mycashrevamp.entity.Transaction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    private int id;
    private String previousHash;

    @OneToMany( mappedBy="transaction_id", fetch= FetchType.EAGER)
    private List<Transaction> transactions;
    private String hash;
    private long timestamp;
    private int nonce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}
