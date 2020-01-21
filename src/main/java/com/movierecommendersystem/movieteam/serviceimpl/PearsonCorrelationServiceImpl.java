package com.movierecommendersystem.movieteam.serviceimpl;

import com.movierecommendersystem.movieteam.service.PearsonCorrelationService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class PearsonCorrelationServiceImpl implements PearsonCorrelationService {

    public double calculateSimilarities(HashMap<Long, Double> userOneViewedMovies,
                                        HashMap<Long, Double> userTwoViewedMovies,
                                        double userOneMeanRating, double userTwoMeanRating,
                                        Set<Long> mutuallyRatedMovies) {
        if (mutuallyRatedMovies.size() < 5) return -1;

        double numerator = 0;
        double userOneDenominator = 0;
        double userTwoDenominator = 0;

        double differenceCurrentRatingFromMeanUserOne;
        double differenceCurrentRatingFromMeanUserTwo;
        double differencesMultiplication;

        for (Long id : mutuallyRatedMovies) {
            differenceCurrentRatingFromMeanUserOne = userOneViewedMovies.get(id) - userOneMeanRating;
            differenceCurrentRatingFromMeanUserTwo = userTwoViewedMovies.get(id) - userTwoMeanRating;
            differencesMultiplication = differenceCurrentRatingFromMeanUserOne *
                    differenceCurrentRatingFromMeanUserTwo;
            numerator = numerator + differencesMultiplication;
            userOneDenominator = userOneDenominator + Math.pow(differenceCurrentRatingFromMeanUserOne, 2);
            userTwoDenominator = userTwoDenominator + Math.pow(differenceCurrentRatingFromMeanUserTwo, 2);
        }
        userOneDenominator = Math.sqrt(userOneDenominator);
        userTwoDenominator = Math.sqrt(userTwoDenominator);
        return (numerator / (userOneDenominator * userTwoDenominator));
    }
}
