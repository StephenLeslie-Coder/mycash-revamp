package com.ctrlaltelite.mycashrevamp.entity;

import com.ctrlaltelite.mycashrevamp.model.Transaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Block {
    @Id
    private int id;
    private String previousHash;
    private Transaction transactions;
    private String hash;
    private long timestamp;
    private int nonce;
}
