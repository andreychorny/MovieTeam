package com.movierecommendersystem.movieteam.service;

import java.util.HashMap;

public interface MovieRatingPredictorService {
    double predictMovieRating(long movieId, long predictedUserId, HashMap<Long,Double> similaritiesWithUsers,
                              HashMap<Long,Double> meanRatingOfUsers);
}
