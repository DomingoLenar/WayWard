package com.example.myfirstapp.modelsV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequest {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
