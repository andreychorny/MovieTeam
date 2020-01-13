package com.movierecommendersystem.movieteam.service;

import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.entity.User;
import com.movierecommendersystem.movieteam.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PearsonCorrelationService {

    @Autowired
    private RatingRepository ratingRepository;

    private HashMap<Long, Double> userOneViewedMovies;
    private Set<Long> mutuallyRatedMovies;
    private HashMap<Long, Double> userTwoViewedMovies;
    private List<Rating> userOneR;
    private List<Rating> userTwoR;
//    public double calculatePersonCorrelationBetweenUsers(User userOne, User userTwo){
//        List<Rating> userOneR = ratingRepository.findRatingsOfUserById(userOne.getId());
//        List<Rating> userTwoR = ratingRepository.findRatingsOfUserById(userTwo.getId());

    public double calculatePersonCorrelationBetweenUsers(long userOneId, long userTwoId){

        readTheRatingsData(userOneId, userTwoId);

        splitRatingsDataOnMaps();

        return processCalculation();
    }

    private Double calculateMeanRatingOfUser(HashMap<Long,Double> allRatedMovies, Set<Long> whichMoviesToCount){
        double userSumRating = 0;
        for (long id: whichMoviesToCount) {
            userSumRating = userSumRating + allRatedMovies.get(id);
        }
        double userMeanRating = userSumRating / whichMoviesToCount.size();
        return userMeanRating;
    }

    private void readTheRatingsData(long userOneId, long userTwoId){
        userOneR = ratingRepository.findRatingsOfUserById(userOneId);
        userTwoR = ratingRepository.findRatingsOfUserById(userTwoId);
    }

    private void splitRatingsDataOnMaps(){
        userOneViewedMovies = new HashMap<>();
        mutuallyRatedMovies = new HashSet<Long>();
        for (Rating rating: userOneR) {
            userOneViewedMovies.put(rating.getMovie_id(),rating.getRating());
        }
        userTwoViewedMovies = new HashMap<>();
        for (Rating rating: userTwoR) {
            userTwoViewedMovies.put(rating.getMovie_id(),rating.getRating());
            if (userOneViewedMovies.containsKey(rating.getMovie_id())) mutuallyRatedMovies.add(rating.getMovie_id());
        }
    }

    private double processCalculation(){

        double userOneMeanRating = calculateMeanRatingOfUser(userOneViewedMovies,mutuallyRatedMovies);
        double userTwoMeanRating = calculateMeanRatingOfUser(userTwoViewedMovies,mutuallyRatedMovies);
        double numerator = 0;
        double userOneDenominator = 0;
        double userTwoDenominator = 0;
        for (Long id: mutuallyRatedMovies) {
            double differenceCurrentRatingFromMeanUserOne = userOneViewedMovies.get(id) - userOneMeanRating;
            double differenceCurrentRatingFromMeanUserTwo = userTwoViewedMovies.get(id) - userTwoMeanRating;
            double differencesMultiplication = differenceCurrentRatingFromMeanUserOne *
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
