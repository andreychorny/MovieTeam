package com.movierecommendersystem.movieteam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(RatingId.class)
public class Rating {

    @Id
    private Long movie_id;

    @Id
    private Long user_id;

    private Double rating;

    public Long getMovie_id() {
        return movie_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Double getRating() {
        return rating;
    }
}
