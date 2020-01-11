package com.movierecommendersystem.movieteam.restcontroller;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HomeRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/home/movies")
    public ResponseEntity getAllMovies(){
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
