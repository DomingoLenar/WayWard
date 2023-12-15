package com.example.myfirstapp.models.UserTasks;

import com.example.myfirstapp.models.DataBase;
import com.example.myfirstapp.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserFetchTask implements Runnable {
    private UserCallback userCallback;
    private String column;
    private String searchKey;

    public UserFetchTask(UserCallback userCallback,  String column, String searchKey){
        this.userCallback = userCallback;
        this.column = column;
        this.searchKey = searchKey;
    }

    @Override
    public void run() {
        try {
            DataBase db = new DataBase();
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_details WHERE "+column+" = '"+searchKey+"'");
            if(rs.next()){
                //Gets data from ResultSet and segregates them into respective variables
                String fetchedUsername = rs.getString(1);
                String fetchedPassword = rs.getString(2);
                int fetchedUserID = rs.getInt(3);
                String fetchedFName = rs.getString(4);
                String fetchedMName = rs.getString(5);
                String fetchedLName = rs.getString(6);
                // constructs and returns a new object of user based on the fetched data
                User user = new User(fetchedUsername, fetchedPassword, true,fetchedFName,fetchedMName, fetchedLName);
                if(userCallback != null){
                    userCallback.onTaskComplete(user);
                }
            }else{
                if(userCallback != null){
                    userCallback.onTaskComplete(null);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
