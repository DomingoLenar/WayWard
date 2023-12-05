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

}
