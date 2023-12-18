package com.example.myfirstapp.modelsV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelPlan {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("reviews")
    @Expose
    private int[] reviews;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("estimated_cost")
    @Expose
    private String estimated_cost;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("destinations")
    @Expose
    private String destinations;

    @SerializedName("author")
    @Expose
    private String author;

    public TravelPlan(int id, String title, int[] reviews, String duration, String estimated_cost, String description, String destinations, String author){
        this.id = id;
        this.title = title;
        this.reviews = reviews;
        this.duration = duration;
        this.estimated_cost = estimated_cost;
        this.description = description;
        this.destinations = destinations;
        this.author = author;
    }

    public TravelPlan(String title, int[] reviews, String duration, String estimated_cost, String description, String destinations, String author){
        this.title = title;
        this.reviews = reviews;
        this.duration = duration;
        this.estimated_cost = estimated_cost;
        this.description = description;
        this.destinations = destinations;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getReviews() {
        return reviews;
    }

    public void setReviews(int[] reviews) {
        this.reviews = reviews;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
