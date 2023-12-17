package com.example.myfirstapp.modelsV2;

public class ContactDetails {
    private Integer id;
    private String username;
    private String email;
    private String address;
    private String number;

    public ContactDetails(int id, String username, String email, String address, String number){
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.number = number;
    }

    public ContactDetails(String username, String email, String address, String number){
        this.username = username;
        this.email = email;
        this.address = address;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
