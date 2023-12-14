package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "balances")
public class Balance {
    @Id
    private int id;
    private int user_id;
    private double balance;

}
