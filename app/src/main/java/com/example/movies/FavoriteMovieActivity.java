package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FavoriteMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);
        RecyclerView recyclerViewFavoriteMovies = findViewById(R.id.recyclerViewFavoriteMovies);
        FavoriteMovieViewModel viewModel = new ViewModelProvider(this)
                .get(FavoriteMovieViewModel.class);
        MovieAdapter movieAdapter = new MovieAdapter();
        recyclerViewFavoriteMovies.setAdapter(movieAdapter);
        recyclerViewFavoriteMovies.setLayoutManager(new GridLayoutManager(this,2));
        viewModel.getAllFavoriteFilms().observe(this, movieAdapter::setMovies);

        movieAdapter.setOnOpenScreenDetailListener(movie -> {
            Intent intent = MovieDetailActivity
                    .newIntent(FavoriteMovieActivity.this, movie);
            startActivity(intent);
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavoriteMovieActivity.class);
    }
}