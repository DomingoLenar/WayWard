package com.example.myfirstapp.models;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TravelPlan {
    private int post_id;
    private String title;
    private ArrayList<Review> reviews;
    private String username;
    private String duration;
    private int estimated_cost;
    private String description;
    private String destinations;
    private DataBase db = new DataBase();

    public TravelPlan(String title, ArrayList<Review> reviews, String username, String duration, int estimated_cost,String description, String destinations){
        this.title = title;
        this.reviews = reviews;
        this.username = username;
        this.duration = duration;
        this.estimated_cost = estimated_cost;
        this.description = description;
        this.destinations = destinations;
    }

    public void insertTravelPlan(){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO travel_plan(id,username,title,reviews,duration,estimated_cost,description,destinations)" +
                    "values");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(int estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }
}
