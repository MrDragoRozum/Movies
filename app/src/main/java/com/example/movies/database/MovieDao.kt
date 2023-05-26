package com.example.movies.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movies.pojo.Movie
@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_favorite")
    fun getAllFavoriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_favorite WHERE id = :id")
    fun getFavoriteMovie(id: Int)

    @Insert
    fun insertMovie(movie: Movie)

    @Query("DELETE FROM movie_favorite WHERE id = :id")
    fun removeMovie(id: Int)
}