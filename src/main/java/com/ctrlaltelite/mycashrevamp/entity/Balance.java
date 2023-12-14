package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Balance {
    @Id
    private int id;
    private int user_id;
    private double balance;

}
