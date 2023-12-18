package com.example.myfirstapp.models;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

@Deprecated
public class Review {
    private User reviewer;
    private int rating;
    private String description;
    private int review_id;
    private DataBase db = new DataBase();

    public Review(User reviewer, int rating, String description, int review_id){
        this.reviewer = reviewer;
        this.rating = rating;
        this.description = description;
        this.review_id = review_id;
    }

    public void insertCurrentReview(){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            st.executeQuery("INSERT INTO review(id, reviewer, rating, description)" +
                    "values(default, '"+this.reviewer.getUsername()+"','"+this.rating+"','"+this.description+"')");
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

}
