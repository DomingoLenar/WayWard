package com.example.myfirstapp.models;

import java.sql.*;
public class DataBase {
    private String url = "jdbc:postgresql://db.fauokmrzqpowzdiqqxxg.supabase.co:5432/postgres";
    private String user = "postgres";
    private String password = "palakapapoy";


    public Connection createConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user, password);
            return connection;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method fetches the user from the database using the parameter username
     * this returns an object of User
     * @param username
     * @return User
     */
    public User fetchUser(String username){
        try{
            Connection conn = createConnection();
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
    public ContactDetails fetchContactDetails(String username){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contact_details WHERE username = '"+username+"'");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
