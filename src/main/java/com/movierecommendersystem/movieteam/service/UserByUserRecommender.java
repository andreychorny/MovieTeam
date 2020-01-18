package com.movierecommendersystem.movieteam.service;

import com.movierecommendersystem.movieteam.entity.Movie;
import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.entity.User;
import com.movierecommendersystem.movieteam.repository.MovieRepository;
import com.movierecommendersystem.movieteam.repository.RatingRepository;
import com.movierecommendersystem.movieteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserByUserRecommender {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieRatingPredictorService movieRatingPredictorService;

    @Autowired
    PearsonCorrelationService pearsonCorrelationService;

    @Autowired
    RatingRepository ratingRepository;

    private List<Rating> ratings = new ArrayList<>();

    private HashMap<Long,Double> meanRatingsOfUsers;

    private HashMap<Long,Double> top50PredictedMovies;

    private long minimumRatedTopMovieId;

    private HashMap<Long,Double> similaritiesWithOtherUsers;

    private long userToCompareWithId;

    public HashMap<Long, Double> makeRecommendation(long comparedUserId){
        List<User> users = (List<User>) userRepository.findAll();
        meanRatingsOfUsers = new HashMap<>();
        similaritiesWithOtherUsers = new HashMap<>();
        for (User userToCompareWith: users) {
            userToCompareWithId = userToCompareWith.getId();
            if(userToCompareWithId != comparedUserId){
               double similarity = pearsonCorrelationService.
                        calculatePersonCorrelationBetweenUsers(comparedUserId,userToCompareWithId);
               //We are interested in making of recommendations only utilizing users with similarity > 0.1
                similaritiesWithOtherUsers.put(userToCompareWithId, similarity);
            }
            meanRatingsOfUsers.put(userToCompareWithId,pearsonCorrelationService.dummyUserTwoMeanGet());
        }
        meanRatingsOfUsers.put(comparedUserId,pearsonCorrelationService.dummyUserOneMeanGet());

        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        top50PredictedMovies = new HashMap<>();
        ratings = (List<Rating>) ratingRepository.findAll();
        for(Movie movie: movies){
            double prediction = movieRatingPredictorService.predictMovieRating(movie.getId(),comparedUserId,
                    similaritiesWithOtherUsers, meanRatingsOfUsers);
            manageTop50Movies(movie.getId(), prediction);
        }
        return top50PredictedMovies;
    }

    private void manageTop50Movies(long movieId, double prediction){
        if(top50PredictedMovies.isEmpty()) minimumRatedTopMovieId = movieId;
        if(top50PredictedMovies.size()<50){
            top50PredictedMovies.put(movieId,prediction);
            if (prediction < top50PredictedMovies.get(minimumRatedTopMovieId)) minimumRatedTopMovieId = movieId;
        }else {
            if (prediction > top50PredictedMovies.get(minimumRatedTopMovieId)){
                top50PredictedMovies.remove(minimumRatedTopMovieId);
                minimumRatedTopMovieId = searchForMinimumValueInMap();
            }
        }
    }

    private long searchForMinimumValueInMap(){
        long minimumElementId = -5;
        for(Map.Entry<Long, Double> entry: top50PredictedMovies.entrySet()){
            if (minimumElementId < 0) minimumElementId = entry.getKey();
            if (top50PredictedMovies.get(minimumElementId)< entry.getValue()) minimumElementId = entry.getKey();
        }
        return minimumElementId;
    }
}
