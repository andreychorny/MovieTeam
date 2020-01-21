package com.movierecommendersystem.movieteam.service;


import java.util.HashMap;
import java.util.Set;

public interface UserSimilaritiesService {

    double calculateSimilarities(HashMap<Long, Double> userOneViewedMovies,
                                 HashMap<Long, Double> userTwoViewedMovies,
                                 double userOneMeanRating, double userTwoMeanRating,
                                 Set<Long> mutuallyRatedMovies);
}
