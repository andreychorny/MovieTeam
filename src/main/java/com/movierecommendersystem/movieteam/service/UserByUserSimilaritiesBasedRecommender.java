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
public class UserByUserSimilaritiesBasedRecommender {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRatingPredictorService movieRatingPredictorService;

    @Autowired
    private PearsonCorrelationService pearsonCorrelationService;

    @Autowired
    private UserRatingProcessingService userRatingProcessingService;

    @Autowired
    private RatingRepository ratingRepository;


    private HashMap<Long, Double> meanRatingsOfUsers;

    private HashMap<Long, Double> similaritiesWithOtherUsers;

    private List<Rating> mainUserRatings;
    private List<Rating> secondaryUserRatings;
    private HashMap<Long, Double> mainUserViewedMovies;
    private HashMap<Long, Double> secondaryUserViewedMovies;
    private double mainUserMeanRating;
    private double secondaryUserMeanRating;

    public List<Movie> makeRecommendation(long mainUserId) {
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
        Set<Long> moviesToIgnore = new HashSet<>(mainUserViewedMovies.keySet());
        List<Movie> moviesToRecommend = new ArrayList<>();
        //we gather top 10 unviewed movies from the N most similar users
        for(int i=0; i<5; i++){
            long biggestSimilarityId = searchForMaxValueInMap(similaritiesWithOtherUsers);
            similaritiesWithOtherUsers.remove(biggestSimilarityId);
            List<Object[]> ratingsAndMovies = ratingRepository.
                    findRatingsAndMoviesOfUserByIdOrderByRatingDESC(biggestSimilarityId);
            int amountOfRecommendedMovies = 0;
            for(Object[] ratingAndMovie : ratingsAndMovies){
                Rating rating = (Rating) ratingAndMovie [0];
                Movie movie = (Movie) ratingAndMovie [1];
                if(!moviesToIgnore.contains(rating.getMovie_id())){
                    moviesToIgnore.add(rating.getMovie_id());
                    moviesToRecommend.add(movie);
                    amountOfRecommendedMovies++;
                }
                if(amountOfRecommendedMovies >= 10) break;
            }
        }
        return moviesToRecommend;
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
            double similarity = pearsonCorrelationService.
                    calculatePersonCorrelationBetweenUsers(mainUserViewedMovies, secondaryUserViewedMovies,
                            mainUserMeanRating, secondaryUserMeanRating, mutuallyRatedMovies);
            //We are interested in making of recommendations only utilizing users with similarity > 0.1
            similaritiesWithOtherUsers.put(secondaryUserId, similarity);
        }
        meanRatingsOfUsers.put(secondaryUserId, secondaryUserMeanRating);
    }

    private long searchForMaxValueInMap(HashMap<Long,Double> similaritiesWithOtherUsers) {
        long maxElementId = -5;
        for (Map.Entry<Long, Double> entry : similaritiesWithOtherUsers.entrySet()) {
            if (maxElementId < 0) maxElementId = entry.getKey();
        }
        return maxElementId;
    }
}
