package com.valleon.moviecatalogservice.resources;

import com.valleon.moviecatalogservice.models.CatalogItem;
import com.valleon.moviecatalogservice.models.Movie;
import com.valleon.moviecatalogservice.models.Rating;
import com.valleon.moviecatalogservice.models.UserRating;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //get all rated movie IDs
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class );

        return ratings.getGetUserRating().stream().map(rating -> {
            // For each movie ID, call movie info service and get details
           Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                          /*  Movie movie = webClientBuilder.build()
                                    .get()
                                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                                    .retrieve()
                                    .bodyToMono(Movie.class)
                                    .block();
                           */
                            //Put them all together
                            return new CatalogItem(movie.getName(), "Description", rating.getRating());
                        }
                )
                .collect(Collectors.toList());
    }
}
