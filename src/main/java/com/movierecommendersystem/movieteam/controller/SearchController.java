package com.movierecommendersystem.movieteam.controller;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model){
        System.out.println("in search controller");
        System.out.println("search title: "+search);
        List<Movie> movies = new ArrayList<>();
        movies = movieRepository.searchByTitle(search);
        model.addAttribute("movies", movies);
        return "search";
    }
}
