package com.example.myfirstapp.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Deprecated
public class DatabaseConnection implements Runnable{
    private String url;
    private String user;
    private String password;

    private Connection connection;

    public DatabaseConnection(String url, String user, String password, Connection connection){
        this.url = url;
        this.user = user;
        this.password = password;
        this.connection = connection;
    }
    @Override
    public void run() {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user, password);
        }catch(ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
