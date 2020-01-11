package com.movierecommendersystem.movieteam.repository;

import com.movierecommendersystem.movieteam.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository  extends CrudRepository<Movie, Long> {

    @Query("select m from Movie m where m.title like %:title%")
    public List<Movie> searchByTitle(@Param("title") String title);


    @Query(value = "select * from Movie m where m.title like %:title% limit 30", nativeQuery = true)
    public List<Movie> findTop30ByTitle(@Param("title") String title);

}
