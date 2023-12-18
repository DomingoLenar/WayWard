package com.example.myfirstapp.models;

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
            ResultSet rs = st.executeQuery("INSERT INTO review(id, reviewer, rating, description)" +
                    "values(default, '"+this.reviewer.getUsername()+"','"+this.rating+"','"+this.description+"')");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
