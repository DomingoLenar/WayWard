package com.example.myfirstapp.models.UserTasks;

import com.example.myfirstapp.models.DataBase;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserUpdateTask implements Runnable{
    private String toUpdate;
    private String newValue;
    private String identifierType;
    private String identifier;
    private UserCallback userCallback;
    public UserUpdateTask(String toUpdate, String newValue,String identifierType,String identifier ,UserCallback userCallback){
        this.toUpdate = toUpdate;
        this.newValue = newValue;
        this.userCallback = userCallback;
        this.identifierType = identifierType;
        this.identifier = identifier;

    }

    public void run(){
        try{

            DataBase db = new DataBase();
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            st.executeQuery("UPDATE user_details SET "+toUpdate+" = '"+newValue+"' WHERE "+identifierType+" = '"+identifier+"'");


        }catch(PSQLException e){
            if("No results were returned by the query.".equals(e.getMessage())){
                System.out.println("Success");
            }else{
                e.printStackTrace();
            }
        }catch(SQLException nE){
            nE.printStackTrace();
        }
    }
}
