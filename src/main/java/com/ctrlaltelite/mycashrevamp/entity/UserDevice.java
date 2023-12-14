package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDevice {
    @Id
    private int device_id;
    private int user_id;
    private String device_name;
    private String device_type;
    private String last_login;

}
