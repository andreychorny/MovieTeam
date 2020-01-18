package com.movierecommendersystem.movieteam.service;

import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MovieRatingPredictorService {

    @Autowired
    private RatingRepository ratingRepository;

    public double predictMovieRating(long movieId, long predictedUserId, HashMap<Long,Double> similaritiesWithUsers,
                                     HashMap<Long,Double> meanRatingOfUsers){
        double predictedRating = 0;
        double numeratorSum = 0;
        double denumeratorSum = 0;
        for (long userId: similaritiesWithUsers.keySet()) {
            Rating rowForThisMovieRatingNeighbor = ratingRepository.findRatingOnMovieOfUserById(movieId, userId);
            if(rowForThisMovieRatingNeighbor != null){
                double ratingForMovieByNeighbor = rowForThisMovieRatingNeighbor.getRating();
                numeratorSum = numeratorSum + (similaritiesWithUsers.get(userId) * (ratingForMovieByNeighbor -
                        meanRatingOfUsers.get(userId)));
                denumeratorSum = denumeratorSum + Math.abs(similaritiesWithUsers.get(userId));
            }
        }
        predictedRating = meanRatingOfUsers.get(predictedUserId) + (numeratorSum / denumeratorSum);
        System.out.println(movieId + " RAT: "+predictedRating);
        return predictedRating;
    }
}
