package com.movierecommendersystem.movieteam.controller;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
import com.movierecommendersystem.movieteam.repository.RatingRepository;
import com.movierecommendersystem.movieteam.repository.UserRepository;
import com.movierecommendersystem.movieteam.service.PearsonCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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
    private PearsonCorrelationService pearsonCorrelationService;

    @GetMapping("/home")
    public String goHome(Model model){
        System.out.println("in home controller");

        List<Movie> movies = new ArrayList<>();
        movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        List<Rating> ratings = new ArrayList<>();
        ratings = ratingRepository.findRatingsOfUserById((long) 1);
        System.out.println(ratings.get(0).getMovie_id());
        System.out.println(ratings.get(0).getRating());
        System.out.println(ratings.get(4).getMovie_id());
        System.out.println(ratings.get(4).getRating());
        System.out.println("!!!!!");
        for(int i = 1; i<610; i++) {
            double correlation = pearsonCorrelationService.calculatePersonCorrelationBetweenUsers(1,i);
            System.out.println("correlation user" + i +" =" + correlation);
        }
        return "index";
    }

    @GetMapping("/goToSearch")
    public String goToSearch(){
        System.out.println("going to search page");
        return "search";
    }

}
