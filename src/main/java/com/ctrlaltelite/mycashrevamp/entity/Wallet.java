package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Wallet {
    @Id
    private int id;
    private int user_id;
    private String wallet_address;
    private List<Balance> balance;
    private String private_key;
    private String public_key;

}
