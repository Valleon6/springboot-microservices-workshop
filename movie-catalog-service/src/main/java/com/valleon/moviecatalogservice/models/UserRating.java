package com.valleon.moviecatalogservice.models;

import java.util.List;

public class UserRating {
    private List<Rating> ratings;
    private String userId;


    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
//    public void setGetUserRating(List<Rating> getUserRating) {
//        this.getUserRating = getUserRating;
//    }
}
