package com.ctrlaltelite.mycashrevamp.bean;

public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter and setter for the token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

