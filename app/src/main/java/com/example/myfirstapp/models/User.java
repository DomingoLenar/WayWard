package com.example.myfirstapp.models;

import com.example.myfirstapp.controllers.SigninController;
import com.example.myfirstapp.controllers.SignupController;
import com.example.myfirstapp.controllers.UserProfileSettingsController;

import org.postgresql.util.PSQLException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private SignupController signupController;
    private SigninController signinController;
    public User (SignupController signupController) {
        this.signupController = signupController;
    }
    public User(SigninController signinController) {
        this.signinController = signinController;
    }
    public User(UserProfileSettingsController userProfileSettingsController) {
    }
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

    /**
     * Inserts the current object of user to the database
     */
    public void insertCurrentUser(){
        if(isUsernameValid()) {
            try {
                Connection conn = db.createConnection();
                Statement st = conn.createStatement();
                st.executeQuery("insert into user_details(user_id,username,password,first_name,middle_name,last_name) values (default,'" + this.username + "','"
                        + this.password + "','" + this.firstName + "','" + this.middleName + "','" + this.lastName + "')");
            }
            catch(PSQLException psqlException){
                if("No results were returned by the query.".equals(psqlException.getMessage())){
                    System.out.println("No results from query, insert success");
                }else{
                    psqlException.printStackTrace();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("username already exists");
        }
    }

    /**
     * This method updates the database with the new password as well as the current object of user's
     * password variable
     * @param rawPassword   The raw un-hashed new password
     */
    public void updatePassword(String rawPassword){
        String newPassword = hashPassword(rawPassword);
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            st.executeQuery("UPDATE user_details SET password = '"+newPassword+"' WHERE username = '"+this.username+"'");
            this.password = newPassword;
        }
        catch(PSQLException psqlException){
            if("No results were returned by the query.".equals(psqlException.getMessage())){
                System.out.println("No results from query, Update success");
            }else{
                psqlException.printStackTrace();
            }
        }
        catch(SQLException updateOfPassword){
            updateOfPassword.printStackTrace();
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
            ResultSet rs = st.executeQuery("SELECT exists(SELECT 1 FROM user_details WHERE username = '"+this.username+"'");
            rs.next();
            return !(rs.getBoolean(1));
        }
        catch(PSQLException psqlException){
            if("No results were returned by the query.".equals(psqlException.getMessage())){
                System.out.println("No results from query, query success");
                return true;
            }else{
                psqlException.printStackTrace();
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return true;
        }
    }

    /**
     * Method used to authenticate given password with the stored password in the database
     * @return boolean
     */
    public boolean authenticate(){
        User dbUser = db.fetchUser(this.username);

        //insert logic to compare two objects of user
        return this.password.equals(dbUser.getPassword());
    }

    /**
     * This method hashes the password to provide a level of security in storing the password
     * @param rawPassword Takes in a string that is a raw unhashed version of the password
     * @return String
     */
    private String hashPassword(String rawPassword){
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Get the byte representation of the input string
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // Handle the exception (e.g., print an error message)
            e.printStackTrace();
            return null;
        }
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


    public boolean createAccount(String email, String username, String password) {

//        db.storeUserAccount()
        return false;
    }

    public boolean logoutUser() {
        // reset states, clear session
        return true;
    }
}
