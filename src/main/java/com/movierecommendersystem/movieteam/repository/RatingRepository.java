package com.movierecommendersystem.movieteam.repository;

import com.movierecommendersystem.movieteam.entity.Rating;
import com.movierecommendersystem.movieteam.entity.RatingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, RatingId> {

    @Query("SELECT r FROM Rating r where r.user_id = :id")
    List<Rating> findRatingsOfUserById(@Param("id") Long id);

    @Query("SELECT r FROM Rating r where r.movie_id =:movieId and r.user_id = :userId")
    Rating findRatingOnMovieOfUserById(@Param("movieId") Long movieId, @Param("userId") Long userId);
}


