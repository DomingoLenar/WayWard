package com.example.myfirstapp.models;

import java.sql.*;
public class User {
    private String username;
    private String password;
    private int userId = -1;
    private String firstName;
    private String middleName;
    private String lastName;
    private final DataBase db = new DataBase();

    public User(String username, String password, boolean hashed){
        this.username = username;
        if(hashed){
            this.password = password;
        }else{
            this.password = hashPassword(password);
        }
        this.firstName = null;
        this.middleName = null;
        this.lastName = null;
    }

    public User(String username, String password, boolean hashed, String firstName, String middleName, String lastName){
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        if(hashed){
            this.password = password;
        }else{
            this.password = hashPassword(password);
        }
    }

    public User fetchUser(String username){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_details WHERE username = "+username);
            if(rs.next()){
                //Gets data from ResultSet and segregates them into respective variables
                String fetchedUsername = rs.getString(1);
                String fetchedPassword = rs.getString(2);
                int fetchedUserID = rs.getInt(3);
                String fetchedFName = rs.getString(4);
                String fetchedMName = rs.getString(5);
                String fetchedLName = rs.getString(6);
                // constructs and returns a new object of user based on the fetched data
                return new User(fetchedUsername, fetchedPassword, true,fetchedFName, fetchedMName, fetchedLName);
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void insertCurrentUser(){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("insert into user_details(user_id,username,password,first_name,middle_name,last_name) values (default"+this.username+","
            +this.password+","+this.firstName+","+this.middleName+","+ this.lastName+")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean isUsernameValid(){
        try {
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT exists(SELECT 1 FROM user_details WHERE username = "+this.username);
            return rs.getBoolean(1);
        }catch(SQLException e){
            e.printStackTrace();
            return true;
        }
    }

    public boolean authenticate(){
        User dbUser = fetchUser(this.username);

        //insert logic to compare two objects of user
        return this.password.equals(dbUser.getPassword());
    }

    private String hashPassword(String rawPassword){
        String hashed="";

        return hashed;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password, boolean hashed){
        if(!hashed){
            this.password = hashPassword(password);
        }else{
            this.password = password;
        }
    }

    public String getUsername(){
        return this.username;

    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstname(String firstName){
        this.firstName = firstName;
    }

    public String getMiddleName(){
        return this.middleName;
    }

    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }


}
