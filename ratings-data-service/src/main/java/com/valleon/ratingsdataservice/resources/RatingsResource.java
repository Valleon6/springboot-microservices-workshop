package com.valleon.ratingsdataservice.resources;

import com.valleon.ratingsdataservice.models.Rating;
import com.valleon.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/ratingsdata")
@RestController
public class RatingsResource {
   @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
            UserRating userRating = new UserRating();
            userRating.initData(userId);
            return userRating;
      }

}
