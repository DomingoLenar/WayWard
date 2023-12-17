package com.example.myfirstapp.modelsV2;

import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;

public class User {
    @SerializedName("id")
    private Integer id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("middle_name")
    private String middle_name;
    @SerializedName("last_name")
    private String last_name;

    public User(int id, String username, String password, boolean hashed, String first_name, String middle_name, String last_name){
        if(hashed){
            this.password = password;
        }else{
            this.password = hashPassword(password);
        }

        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
    }

    public User(String username, String password, boolean hashed, String first_name, String middle_name, String last_name){
        this.username = username;
        if(hashed){
            this.password = password;
        }else{
            this.password = hashPassword(password);
        }
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
    }

    public User(int id, String username, String password, boolean hashed){
        this.username = username;
        if(hashed){
            this.password = password;
        }else{
            this.password = hashPassword(password);
        }
    }


    private String hashPassword(String password){
        String output = password;

        return output;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
