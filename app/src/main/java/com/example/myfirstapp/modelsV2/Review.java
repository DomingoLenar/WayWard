package com.example.myfirstapp.modelsV2;

public class Review {

    private Integer id;
    private String reviewer;
    private Integer rating;
    private String description;

    public Review(int id, String reviewer, int rating, String description){
        this.id = id;
        this.reviewer = reviewer;
        this.rating = rating;
        this.description = description;
    }

    public Review(String reviewer, int rating, String description){
        this.reviewer = reviewer;
        this.rating = rating;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
