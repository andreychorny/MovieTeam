package com.movierecommendersystem.movieteam.service;

import com.movierecommendersystem.movieteam.entity.Movie;

import java.util.List;

public interface UserByUserSimilaritiesBasedRecommender{

    List<Movie> makeRecommendation(long mainUserId);

}
