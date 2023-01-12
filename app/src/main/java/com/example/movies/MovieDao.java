package com.example.movies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie_favorite")
    LiveData<List<Movie>> getAllFavoriteMovies();

    @Query("SELECT * FROM MOVIE_FAVORITE WHERE id = :id")
    LiveData<List<Movie>> getFavoriteMovie(int id);

    @Insert
    Completable insertMovie(Movie movie);

    @Query("DELETE FROM MOVIE_FAVORITE WHERE id = :id")
    Completable removeMovie(int id);
}
