package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        movieAdapter = new MovieAdapter();
        recyclerViewMovies.setAdapter(movieAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getMovies().observe(this, movies -> movieAdapter.setMovies(movies));
        mainViewModel.loadMovies();
    }
}