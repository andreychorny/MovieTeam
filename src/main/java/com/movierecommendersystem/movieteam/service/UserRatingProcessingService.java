package com.movierecommendersystem.movieteam.service;

import com.movierecommendersystem.movieteam.entity.Rating;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserRatingProcessingService {

    Double calculateMeanRatingOfUser(HashMap<Long, Double> allRatedMovies);

    HashMap<Long, Double> convertRatingsToMaps(List<Rating> userRatings);

    Set<Long> findMutuallyViewedMovies(Map<Long,Double> userOneViewedMovies,
                                       Map<Long,Double> userTwoViewedMovies);

}
