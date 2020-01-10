package com.movierecommendersystem.movieteam.controller;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
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

    @GetMapping("/home")
    public String goHome(Model model){
        System.out.println("in home controller");

        List<Movie> movies = new ArrayList<>();
        movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        System.out.println(movies.size());
        System.out.println(movies.get(0).getId());
        System.out.println(movies.get(0).getTitle());
        System.out.println(movies.get(6).getId());
        System.out.println(movies.get(6).getTitle());
        return "index";
    }

}
