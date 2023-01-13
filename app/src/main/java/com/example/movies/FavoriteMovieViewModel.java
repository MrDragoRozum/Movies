package com.example.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoriteMovieViewModel extends AndroidViewModel {

    private final MovieDao movieDao;

    public FavoriteMovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getAllFavoriteFilms() {
        return movieDao.getAllFavoriteMovies();
    }

    public LiveData<Movie> getFavoriteFilms(int id) {
        return movieDao.getFavoriteMovie(id);
    }

}
