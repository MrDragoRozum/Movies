package com.example.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.adapters.MovieAdapter
import com.example.movies.databinding.ActivityFavoriteMovieBinding

class FavoriteMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteMovieBinding
    private lateinit var viewModel: FavoriteMovieViewModel
    private val adapter = MovieAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        installAdapter()
        observe()
    }

    private fun observe() {
        viewModel = ViewModelProvider(this)[FavoriteMovieViewModel::class.java]
        viewModel.getAllFavoriteFilms.observe(this) { adapter.movieList = it }
    }

    private fun installAdapter() {
        binding.recyclerViewFavoriteMovies.adapter = adapter
        binding.recyclerViewFavoriteMovies.layoutManager = GridLayoutManager(this, 2)
        listener()
    }

    private fun listener() {
        adapter.onOpenScreenDetailListener = MovieAdapter.OnOpenScreenDetailListener {
            val intent = MovieDetailActivity.newIntent(this@FavoriteMovieActivity, it)
            startActivity(intent)
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, FavoriteMovieActivity::class.java)
    }
}