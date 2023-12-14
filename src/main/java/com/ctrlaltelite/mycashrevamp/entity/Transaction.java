package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private int transaction_id;
    private String sender_address;
    private String receiver_address;
    private double amount;
    private String transaction_hash;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Block block;
    private LocalDate timestamp;
}
