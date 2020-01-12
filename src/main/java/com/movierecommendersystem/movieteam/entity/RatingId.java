package com.movierecommendersystem.movieteam.entity;

import java.io.Serializable;
import java.util.Objects;

public class RatingId implements Serializable {

    private Long movie_id;

    private Long user_id;

    public RatingId() {
    }

    public RatingId(Long movie_id, Long user_id) {
        this.movie_id = movie_id;
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId that = (RatingId) o;
        return movie_id.equals(that.movie_id) &&
                user_id.equals(that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie_id, user_id);
    }
}
