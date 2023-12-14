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
}
