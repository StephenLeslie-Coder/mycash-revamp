package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {

    @Id
    private int id;
    private int user_id;
    private String account_number;
    private String routing_number;
    private String account_type;
    private String created_at;

}
