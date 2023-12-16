package com.example.myfirstapp.models.UserTasks;

import com.example.myfirstapp.models.DataBase;
import com.example.myfirstapp.models.User;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInsertTask implements Runnable {
    private User user;
    private UserCallback userCallback;

    public UserInsertTask(User user, UserCallback userCallback){
        this.user = user;
        this.userCallback = userCallback;
    }

    public void run(){
        try{
            DataBase db = new DataBase();
            Statement st;
            try (Connection conn = db.createConnection()) {
                st = conn.createStatement();
            }
            st.executeQuery("insert into user_details(user_id,username,password,first_name,middle_name,last_name) values (default,'" + user.getUsername() + "','"
                    + user.getPassword() + "','" + user.getFirstName() + "','" + user.getMiddleName() + "','" + user.getLastName() + "')");

        }catch(PSQLException pE){
            if("No results were returned by the query.".equals(pE.getMessage())){
                System.out.println("Success");
            }else{
                pE.printStackTrace();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

}
