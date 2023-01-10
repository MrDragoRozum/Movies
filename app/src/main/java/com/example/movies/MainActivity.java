package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBarLoading;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        movieAdapter = new MovieAdapter();
        recyclerViewMovies.setAdapter(movieAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getMovies().observe(this, movies -> movieAdapter.setMovies(movies));
        mainViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBarLoading.setVisibility(View.VISIBLE);
            } else {
                progressBarLoading.setVisibility(View.GONE);
            }
        });

        movieAdapter.setOnReachEndListener(() -> mainViewModel.loadMovies());
        movieAdapter.setOnOpenScreenDetailListener(movie -> {
            Intent intent = MovieDetailActivity.newIntent(MainActivity.this, movie);
            startActivity(intent);
        });
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBarLoading = findViewById(R.id.progressBarLoading);
    }
}