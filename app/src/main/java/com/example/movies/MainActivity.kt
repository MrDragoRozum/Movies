package com.example.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
            onOpenScreenDetailListener = MovieAdapter.OnOpenScreenDetailListener {
                val intent = MovieDetailActivity.newIntent(this@MainActivity, it)
                startActivity(intent)
            }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemFavorite) {
            val intent = FavoriteMovieActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}