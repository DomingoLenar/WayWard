package com.example.myfirstapp.models;

import java.sql.*;

/**
 * User model class
 */
public class User {
    private String username;
    private String password;
    private int userId = -1;
    private String firstName;
    private String middleName;
    private String lastName;
    private final DataBase db = new DataBase();

    /**
     * This constructor takes in three parameters and leaves the details of names as null
     * @param username
     * @param password
     * @param hashed
     */
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

    /**
     * Is the complete constructor of the class
     * @param username
     * @param password
     * @param hashed
     * @param firstName
     * @param middleName
     * @param lastName
     */
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

    //This should be moved to DataBase class in further revisions
    /**
     * This method fetches the user from the database using the parameter username
     * this returns an object of User
     * @param username
     * @return User
     */
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

    /**
     * Inserts the current object of user to the database
     */
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

    /**
     * Checks if the username already exists in the database
     * @return boolean
     */
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

    /**
     * Method used to authenticate given password with the stored password in the database
     * @return boolean
     */
    public boolean authenticate(){
        User dbUser = fetchUser(this.username);

        //insert logic to compare two objects of user
        return this.password.equals(dbUser.getPassword());
    }

    /**
     * This method hashes the password to provide a level of security in storing the password
     * @param rawPassword Takes in a string that is a raw unhashed version of the password
     * @return String
     */
    private String hashPassword(String rawPassword){
        String hashed="";

        return hashed;
    }

    /**
     *
     * @return
     */
    public String getPassword(){
        return this.password;
    }

    /**
     *
     * @return
     */
    public void setPassword(String password, boolean hashed){
        if(!hashed){
            this.password = hashPassword(password);
        }else{
            this.password = password;
        }
    }

    /**
     *
     * @return
     */
    public String getUsername(){
        return this.username;

    }

    /**
     *
     * @return
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     *
     * @return
     */
    public void setFirstname(String firstName){
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getMiddleName(){
        return this.middleName;
    }

    /**
     *
     * @return
     */
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    /**
     *
     * @return
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     *
     * @return
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


}
