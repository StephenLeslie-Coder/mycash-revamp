package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Contacts {

    @Id
    private int contact_id;
    private int user_id;
    private String contact_name;
    private String contact_username;
    private String contact_email;
    private LocalDate added_at;
}
