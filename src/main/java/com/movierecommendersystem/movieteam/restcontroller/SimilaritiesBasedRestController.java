package com.movierecommendersystem.movieteam.restcontroller;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.service.UserByUserSimilaritiesBasedRecommender;
import com.movierecommendersystem.movieteam.serviceimpl.UserByUserSimilaritiesBasedRecommenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SimilaritiesBasedRestController {

    @Autowired
    private UserByUserSimilaritiesBasedRecommender userByUserSimilaritiesBasedRecommender;

    @GetMapping("/rest/similar")
    public ResponseEntity getMoviesByRequestParam(@RequestParam("id") Long id){
        List<Movie> recommendation = userByUserSimilaritiesBasedRecommender.makeRecommendation(id);
        return new ResponseEntity<>(recommendation, HttpStatus.OK);
    }

    @GetMapping("/rest/similar/{id}")
    public ResponseEntity getMoviesByPathVariable(@PathVariable("id") Long id){
        List<Movie> recommendation = userByUserSimilaritiesBasedRecommender.makeRecommendation(id);
        return new ResponseEntity<>(recommendation, HttpStatus.OK);
    }
}
