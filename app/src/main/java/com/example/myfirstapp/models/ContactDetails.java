package com.example.myfirstapp.models;

import org.postgresql.util.PSQLException;

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

    /**
     * Default constructor
     * @param username  Username of the user to whom these details belong to
     * @param email Email of the user
     * @param phoneNumber   Phone number of the user
     * @param address   Address of the user
     */
    public ContactDetails(String username, String email, String phoneNumber, String address){
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Alternative constructor for when if the phone number and address is not provided
     * @param username  Username of the user to whom these details belong to
     * @param email Email of the user
     */
    public ContactDetails(String username,  String email){
        this.username = username;
        this.email = email;
        this.address = null;
        this.phoneNumber = null;
    }

    /**
     * Inserts current instance of the object into the database
     */
    public void insertCurrentCD(){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            st.executeQuery("INSERT INTO contact_details (id, username, email, address, number) values (default, '"+this.username+"','"+this.email+"','"+this.address+"','"+this.phoneNumber+"')");
        }
        catch(PSQLException psqlException){
            if("No results were returned by the query.".equals(psqlException.getMessage())){
                System.out.println("No results from query, insert success");
            }else{
                psqlException.printStackTrace();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates the database with the new address
     * @param newAddress    New address to be used to replace the old one
     */
    public void updateAddress(String newAddress){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            st.executeQuery("UPDATE contact_details SET address = '"+newAddress+"' WHERE username = '"+this.username+"'");
        }catch(PSQLException psqlException){
            if("No results were returned by the query.".equals(psqlException.getMessage())){
                System.out.println("No results from query, Update success");
            }else{
                psqlException.printStackTrace();
            }
        }
        catch(SQLException updateAddressException){
            updateAddressException.printStackTrace();
        }
    }

    /**
     * Updates the database with the new phone number
     * @param newNumber     New phone number to replace the old one
     */
    public void updateNumber(String newNumber){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            st.executeQuery("UPDATE contact_details SET address = '"+newNumber+"' WHERE username = '"+this.username+"'");
        }
        catch(PSQLException psqlException){
            if("No results were returned by the query.".equals(psqlException.getMessage())){
                System.out.println("No results from query, Update success");
            }else{
                psqlException.printStackTrace();
            }
        }
        catch(SQLException updateNumberException){
            updateNumberException.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }
}
