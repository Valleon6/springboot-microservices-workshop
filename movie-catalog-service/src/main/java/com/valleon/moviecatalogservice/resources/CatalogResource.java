package com.valleon.moviecatalogservice.resources;

import com.valleon.moviecatalogservice.models.CatalogItem;
import com.valleon.moviecatalogservice.models.UserRating;
import com.valleon.moviecatalogservice.services.MovieInfo;
import com.valleon.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MovieInfo movieInfo;
    @Autowired
    private UserRatingInfo userRatingInfo;


//    @Autowired
//    private DiscoveryClient discoveryClient;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //get all rated movie IDs
        UserRating userRating = userRatingInfo.getUserRating(userId);

        // For each movie ID, call movie info service and get details
        return userRating.getRatings().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }


}
