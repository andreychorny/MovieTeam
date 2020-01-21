package com.movierecommendersystem.movieteam.service;

import com.movierecommendersystem.movieteam.entity.Movie;

import java.util.HashMap;
import java.util.List;

public interface UserByUserMovieRatingPredictor {

    HashMap<Long, Double> makeMovieRatingPrediction(long mainUserId);

}
