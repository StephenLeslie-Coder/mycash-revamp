package com.ctrlaltelite.mycashrevamp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @ManyToMany
    @JoinTable(
            name = "wallet_balance_mapping",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "balance_id")
    )
    private List<Balance> balances;
    private byte[] private_key;
    private byte[] public_key;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public byte[] getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(byte[] private_key) {
        this.private_key = private_key;
    }

    public byte[] getPublic_key() {
        return public_key;
    }

    public void setPublic_key(byte[] public_key) {
        this.public_key = public_key;
    }
}
