package com.example.myfirstapp.models;

import java.sql.*;
public class DataBase {
    private String url = "jdbc:postgresql://db.fauokmrzqpowzdiqqxxg.supabase.co:5432/postgres";
    private String user = "postgres";
    private String password = "palakapapoy";


    /**
     * This method establishes a connection with the POSTGRES database
     * @return Returns an object of connection
     */
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
     * @param username username to fetch in the database
     * @return object of user
     */
    public User fetchUser(String username){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_details WHERE username = '"+username+"'");
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
     * This method fetches the contact details of a given user using the username
     * @param username  User to fetcch contact details of
     * @return  Returns an object of ContactDetails
     */
    public ContactDetails fetchContactDetails(String username){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contact_details WHERE username = '"+username+"'");
            int fetchedID = rs.getInt(1);
            String fetchedUsername = rs.getString(2);
            String fetchedEmail = rs.getString(3);
            String fetchedAddress = rs.getString(4);
            String fetchedNumber = rs.getString(5);
            ContactDetails cd = new ContactDetails(fetchedUsername, fetchedEmail,fetchedNumber,fetchedAddress);
            cd.setId(fetchedID);
            return cd;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetches the travel plan given a search key and column
     * @param columnName    Column to search at
     * @param searchKey     Search key to be used at column
     * @return  Returns an object of TravelPlan
     */
    public TravelPlan fetchTravelPlan(String columnName, String searchKey){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM travel_plan WHERE "+columnName+" = '"+searchKey+"'");
            String fetchedTitle = rs.getString(3);
            String fetchedReviews = rs.getString(4);
            String fetchedAuthor = rs.getString(2);
            String fetchedDuration = rs.getString(5);
            String fetchedEstimatedCost = rs.getString(6);
            String fetchedDescription = rs.getString(7);
            String fetchedDestinations = rs.getString(8);
            TravelPlan fetchedPlan = new TravelPlan(fetchedTitle, null, fetchedAuthor,fetchedDuration, fetchedEstimatedCost,fetchedDescription,fetchedDestinations);
            fetchedPlan.setReviews(fetchedReviews);
            fetchedPlan.setPost_id(rs.getInt(1));
            return fetchedPlan;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method searches a given table using given column and search key
     * @param table Table to search
     * @param column    Column to search
     * @param searchKey Search key to be used for searching
     * @return  Returns the id of the row
     */
    public int search(String table, String column, String searchKey){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM "+table+" WHERE "+column+" = '"+searchKey+"'");
            return rs.getInt(1);
        }catch(SQLException search){
            search.printStackTrace();
        }

        return -1;
    }


}
