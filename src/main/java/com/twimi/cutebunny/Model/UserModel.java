package com.twimi.cutebunny.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserModel {
    private int uid;
    private String username;
    @JsonIgnore
    private String password;
    private String email;

    public UserModel() {

    }

    public UserModel(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
