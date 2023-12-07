package com.example.myfirstapp.models;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TravelPlan {
    private int post_id =-1;
    private String title;
    private ArrayList<Integer> reviews;
    private String author;
    private String duration;
    private String estimated_cost;
    private String description;
    private String destinations;
    private DataBase db = new DataBase();

    public TravelPlan(String title, ArrayList<Integer> reviews, String username, String duration, String estimated_cost,String description, String destinations){
        this.title = title;
        this.reviews = reviews;
        this.author = username;
        this.duration = duration;
        this.estimated_cost = estimated_cost;
        this.description = description;
        this.destinations = destinations;
    }

    public void insertTravelPlan(){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            String csvReviews = createReviewCSV(this.reviews);
            ResultSet rs = st.executeQuery("INSERT INTO travel_plan(id,username,title,reviews,duration,estimated_cost,description,destinations)" +
                    "values(default, '"+this.author+"','"+this.title+"','"+csvReviews+"','"+this.duration+"','"+this.estimated_cost+"','"+this.description+"','"+this.destinations+"')");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateDescription(String newDescription){
        try{
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            if(this.post_id == -1) {
                ResultSet rs = st.executeQuery("UPDATE travel_plan SET description = '" + newDescription + "' WHERE title = '" + this.title + "' and author = '" + this.author + "'");
                this.description = newDescription;
            }else{
                ResultSet rs = st.executeQuery("UPDATE travel_plan SET description = '" + newDescription + "' WHERE id = '" + this.post_id + "'");
                this.description = newDescription;
            }
        }catch(SQLException updateDescriptionError){
            updateDescriptionError.printStackTrace();
        }
    }

    public void updateTitle(String newTitle){
        try{
            if(this.post_id != -1) {
                Connection conn = db.createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("UPDATE travel_plan SET title = '" + newTitle + "' WHERE id = '" + this.post_id + ";");
                this.title = newTitle;
            }else{
                throw new RuntimeException("This travel plan does not have a post id");
            }
        }catch(SQLException updateTitleException){
            updateTitleException.printStackTrace();
        }
    }

    public String createReviewCSV(ArrayList<Integer> arrayList){
        String output = "";
        output += String.valueOf(arrayList.get(0));
        for(int x = 1; x < arrayList.size(); x++){
            output += ", "+String.valueOf(arrayList.get(x));
        }
        return output;
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

    public ArrayList<Integer> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Integer> reviews) {
        this.reviews = reviews;
    }
    //Setter override to process raw string from database
    public void setReviews(String csvReviews){
        ArrayList<Integer> parsedReviews = new ArrayList<Integer>();
        String[] rawReviews = csvReviews.split(",");
        for(int x = 0; x < rawReviews.length; x++){
            parsedReviews.add(Integer.parseInt(rawReviews[x]));
        }
        this.reviews = parsedReviews;
    }

    public String getUsername() {
        return author;
    }

    public void setUsername(String username) {
        this.author = username;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(String estimated_cost) {
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
