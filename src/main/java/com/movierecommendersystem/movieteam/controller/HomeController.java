package com.movierecommendersystem.movieteam.controller;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
import com.movierecommendersystem.movieteam.repository.RatingRepository;
import com.movierecommendersystem.movieteam.repository.UserRepository;
import com.movierecommendersystem.movieteam.service.UserByUserMovieRatingPredictor;
import com.movierecommendersystem.movieteam.service.UserByUserSimilaritiesBasedRecommender;
import com.movierecommendersystem.movieteam.serviceimpl.UserByUserMovieRatingPredictorImpl;
import com.movierecommendersystem.movieteam.serviceimpl.UserByUserSimilaritiesBasedRecommenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserByUserMovieRatingPredictor userByUserMovieRatingPredictor;

    @Autowired
    private UserByUserSimilaritiesBasedRecommender userByUserSimilaritiesBasedRecommender;

    @GetMapping("/home")
    public String goHome(Model model){
        System.out.println("in home controller");

        List<Movie> movies;
        movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        List<Rating> ratings;
        ratings = ratingRepository.findRatingsOfUserById((long) 1);
        System.out.println(ratings.get(0).getMovie_id());
        System.out.println(ratings.get(0).getRating());
        System.out.println(ratings.get(4).getMovie_id());
        System.out.println(ratings.get(4).getRating());
        System.out.println("!!!!!");
        List<Movie> movies2;
//        movies2 = userByUserMovieRatingPredictor.makeRecommendation(1);
//        for(Map.Entry<Long,Double> entry : movies2.entrySet()){
//            int id = Math.toIntExact(entry.getKey());
//            System.out.println(movies.get(id).getId() + "  TITLE: "+ movies.get(id).getTitle());
//        }
        movies2 = userByUserSimilaritiesBasedRecommender.makeRecommendation(1);
        for(Movie movie : movies2){
            System.out.println("movId:" + movie.getId() + " title:" + movie.getTitle());
        }

        return "index";
    }

    @GetMapping("/goToSearch")
    public String goToSearch(){
        System.out.println("going to search page");
        return "search";
    }

}
