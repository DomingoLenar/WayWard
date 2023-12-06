package com.example.myfirstapp.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactDetails {
    private String username;
    private String address;
    private String email;
    private String phoneNumber;
    private int id = -1;
    private DataBase db = new DataBase();
    public ContactDetails(String username,  String email){
        this.username = username;
        this.email = email;
        this.address = null;
        this.phoneNumber = null;
    }

    public ContactDetails(String username, String email, String phoneNumber, String address){
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void insertCurrentCD(){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO contact_details (id, username, email, address, number) values (default, '"+this.username+"','"+this.email+"','"+this.address+"','"+this.phoneNumber+"')");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setId(int id){
        this.id = id;
    }
}
