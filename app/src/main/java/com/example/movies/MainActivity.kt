package com.example.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.adapters.MovieAdapter
import com.example.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val movieAdapter = MovieAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        installAdapter()
        observes()
    }

    private fun installAdapter() {
        binding.recyclerViewMovies.adapter = movieAdapter
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        listeners()
    }

    private fun listeners() {
        with(movieAdapter) {
            onReachEndListener = MovieAdapter.OnReachEndListener { mainViewModel.loadMovies() }
            onOpenScreenDetailListener = MovieAdapter.OnOpenScreenDetailListener {}
        }
    }

    private fun observes() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.getMovies.observe(this) { movieAdapter.movieList = it }
        mainViewModel.getIsLoading.observe(this) {
            if (it) binding.progressBarLoading.visibility = View.VISIBLE
            else binding.progressBarLoading.visibility = View.GONE
        }

    }
}