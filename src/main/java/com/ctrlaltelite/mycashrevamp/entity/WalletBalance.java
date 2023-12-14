package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WalletBalance {
    @Id
    private String balance_id;
    private String wallet_id;
    private String cryptocurrency_type;
    private double balance;

}
