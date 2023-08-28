package org.binay.moviecatelogservice.resource;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.binay.moviecatelogservice.model.CatalogItem;
import org.binay.moviecatelogservice.model.Movie;
import org.binay.moviecatelogservice.model.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final String CATALOG_SERVICE = "catalog-service";
    public static final Logger LOGGER = LoggerFactory.getLogger("CatalogResource");
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    @CircuitBreaker(name = CATALOG_SERVICE, fallbackMethod = "getAvailableCatalogItem")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        LOGGER.info("Debug message -- BINAY");
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        return userRating.ratings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.movieId(), Movie.class);
                    return new CatalogItem(movie.name(), movie.description(), rating.rating());
                })
                .collect(Collectors.toList());

    }

    public List<CatalogItem> getAvailableCatalogItem(Exception e){
        LOGGER.error("Error while calling to downstream api : {}", e);
        return List.of(new CatalogItem("Avatar", "Adventure movie", 5));
    }
}
