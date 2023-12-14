package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    private int transaction_id;
    private String sender_address;
    private String receiver_address;
    private double amount;
    private String transaction_hash;
    private int block_number;
    private LocalDate timestamp;
}
