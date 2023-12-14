package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "id")
    private User user;
    private String wallet_address;

    @OneToMany(targetEntity = Balance.class, mappedBy = "id", fetch = FetchType.EAGER)
    private List<Balance> balance;
    private String private_key;
    private String public_key;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
