package com.example.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movies.database.MovieDatabase

class FavoriteMovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao = MovieDatabase.getInstance(application).movieDao()
    val getAllFavoriteFilms get() = movieDao.getAllFavoriteMovies()
}