package com.movierecommendersystem.movieteam.service;

import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRatingProcessingService {

    @Autowired
    private RatingRepository ratingRepository;

    List<Rating> readTheRatingsData(long userId) {
        return ratingRepository.findRatingsOfUserById(userId);
    }

    Double calculateMeanRatingOfUser(HashMap<Long, Double> allRatedMovies) {
        double userSumRating = 0;
        for (long id : allRatedMovies.keySet()) {
            userSumRating = userSumRating + allRatedMovies.get(id);
        }
        double userMeanRating = userSumRating / allRatedMovies.size();
        return userMeanRating;
    }

    HashMap<Long, Double> convertRatingsToMaps(List<Rating> userRatings) {
        HashMap<Long, Double> userViewedMovies;
        userViewedMovies = new HashMap<>();
        for (Rating rating : userRatings) {
            userViewedMovies.put(rating.getMovie_id(), rating.getRating());
        }
        return userViewedMovies;
    }

    Set<Long> findMutuallyViewedMovies(Map<Long,Double> userOneViewedMovies,
                                               Map<Long,Double> userTwoViewedMovies){
        Set<Long> mutuallyRatedMovies = new HashSet<>();
        for(Map.Entry<Long,Double> entry: userTwoViewedMovies.entrySet()){
            if (userOneViewedMovies.containsKey(entry.getKey())) mutuallyRatedMovies.add(entry.getKey());
        }
        return mutuallyRatedMovies;
    }
}
