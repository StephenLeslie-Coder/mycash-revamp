package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    private int id;
    private int user_id;
    private String card_number;
    private String cardholder_name;
    private String expiration_date;
    private String cvv;
    private String created_at;

}
