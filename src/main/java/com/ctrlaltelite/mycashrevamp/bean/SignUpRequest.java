package com.ctrlaltelite.mycashrevamp.bean;

import lombok.Data;

@Data
public class SignUpRequest {

    String username;

    String password;
    String email;
    Boolean isAdmin;

}
