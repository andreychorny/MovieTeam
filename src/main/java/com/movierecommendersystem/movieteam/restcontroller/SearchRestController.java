package com.movierecommendersystem.movieteam.restcontroller;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SearchRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/rest/movies")
    public ResponseEntity getMoviesByRequestParam(@RequestParam("name") String name){
        List<Movie> movies = movieRepository.findTop30ByTitle(name);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/rest/movies/{name}")
    public ResponseEntity getMoviesByPathVariable(@PathVariable("name") String name){
        List<Movie> movies = movieRepository.findTop30ByTitle(name);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
