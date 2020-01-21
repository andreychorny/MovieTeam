package com.movierecommendersystem.movieteam.serviceimpl;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.entity.User;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
import com.movierecommendersystem.movieteam.repository.RatingRepository;
import com.movierecommendersystem.movieteam.repository.UserRepository;
import com.movierecommendersystem.movieteam.service.MovieRatingPredictorService;
import com.movierecommendersystem.movieteam.service.UserByUserMovieRatingPredictor;
import com.movierecommendersystem.movieteam.service.UserSimilaritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserByUserMovieRatingPredictorImpl implements UserByUserMovieRatingPredictor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRatingPredictorService movieRatingPredictorService;

    @Autowired
    private UserSimilaritiesService userSimilaritiesService;

    @Autowired
    private UserRatingProcessingServiceImpl userRatingProcessingService;

    @Autowired
    private RatingRepository ratingRepository;

    private List<Rating> ratings = new ArrayList<>();

    private HashMap<Long, Double> meanRatingsOfUsers;

    private HashMap<Long, Double> top50PredictedMovies;

    private long minimumRatedTopMovieId;

    private HashMap<Long, Double> similaritiesWithOtherUsers;

    private List<Rating> mainUserRatings;
    private List<Rating> secondaryUserRatings;
    private HashMap<Long, Double> mainUserViewedMovies;
    private HashMap<Long, Double> secondaryUserViewedMovies;
    private double mainUserMeanRating;
    private double secondaryUserMeanRating;


    public HashMap<Long, Double> makeMovieRatingPrediction(long mainUserId) {
        List<User> users = (List<User>) userRepository.findAll();
        meanRatingsOfUsers = new HashMap<>();
        similaritiesWithOtherUsers = new HashMap<>();
        readAllDataForMainUser(mainUserId);
        meanRatingsOfUsers.put(mainUserId, mainUserMeanRating);
        for (User secondaryUser : users) {
            long secondaryUserId = secondaryUser.getId();
            readAllDataForSecondaryUser(secondaryUserId);
            Set<Long> mutuallyRatedMovies = userRatingProcessingService.findMutuallyViewedMovies(mainUserViewedMovies,
                    secondaryUserViewedMovies);
            calculateOtherUsersSimilaritiesAndMeanRatings(mainUserId, secondaryUserId, mutuallyRatedMovies);
        }
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        top50PredictedMovies = new HashMap<>();
        ratings = (List<Rating>) ratingRepository.findAll();
        for (Movie movie : movies) {
            double prediction = movieRatingPredictorService.predictMovieRating(movie.getId(), mainUserId,
                    similaritiesWithOtherUsers, meanRatingsOfUsers);
            manageTop50Movies(movie.getId(), prediction);
        }
        return top50PredictedMovies;
    }

    private void readAllDataForMainUser(long mainUserId) {
        mainUserRatings = userRatingProcessingService.readTheRatingsData(mainUserId);
        mainUserViewedMovies = userRatingProcessingService.convertRatingsToMaps(mainUserRatings);
        mainUserMeanRating = userRatingProcessingService.calculateMeanRatingOfUser(mainUserViewedMovies);
    }

    private void readAllDataForSecondaryUser(long secondaryUserId) {
        secondaryUserRatings = userRatingProcessingService.readTheRatingsData(secondaryUserId);
        secondaryUserViewedMovies = userRatingProcessingService.convertRatingsToMaps(secondaryUserRatings);
        secondaryUserMeanRating = userRatingProcessingService.calculateMeanRatingOfUser(secondaryUserViewedMovies);
    }

    private void calculateOtherUsersSimilaritiesAndMeanRatings(long mainUserId, long secondaryUserId,
                                                               Set<Long> mutuallyRatedMovies) {
        if (secondaryUserId != mainUserId) {
            double similarity = userSimilaritiesService.
                    calculateSimilarities(mainUserViewedMovies, secondaryUserViewedMovies,
                            mainUserMeanRating, secondaryUserMeanRating, mutuallyRatedMovies);
            //We are interested in making of recommendations only utilizing users with similarity > 0.1
            similaritiesWithOtherUsers.put(secondaryUserId, similarity);
        }
        meanRatingsOfUsers.put(secondaryUserId, secondaryUserMeanRating);
    }


    private void manageTop50Movies(long movieId, double prediction) {
        if (top50PredictedMovies.isEmpty()) minimumRatedTopMovieId = movieId;
        if (top50PredictedMovies.size() < 50) {
            top50PredictedMovies.put(movieId, prediction);
            if (prediction < top50PredictedMovies.get(minimumRatedTopMovieId)) minimumRatedTopMovieId = movieId;
        } else {
            if (prediction > top50PredictedMovies.get(minimumRatedTopMovieId)) {
                top50PredictedMovies.remove(minimumRatedTopMovieId);
                minimumRatedTopMovieId = searchForMinimumValueInMap();
            }
        }
    }

    private long searchForMinimumValueInMap() {
        long minimumElementId = -5;
        for (Map.Entry<Long, Double> entry : top50PredictedMovies.entrySet()) {
            if (minimumElementId < 0) minimumElementId = entry.getKey();
            if (top50PredictedMovies.get(minimumElementId) < entry.getValue()) minimumElementId = entry.getKey();
        }
        return minimumElementId;
    }
}
