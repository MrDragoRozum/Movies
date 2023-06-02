package com.example.movies

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movies.adapters.ReviewAdapter
import com.example.movies.adapters.TrailerAdapter
import com.example.movies.databinding.ActivityMovieDetailBinding
import com.example.movies.pojo.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel
    private val trailerAdapter = TrailerAdapter()
    private val reviewAdapter = ReviewAdapter()
    private lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        movie = intent.getSerializableExtra(MOVIE_KEY) as Movie
        installAdapters()
        observes()
        customizingViews()
    }

    private fun customizingViews() {
        Glide.with(this).load(movie.poster.url).into(binding.imageViewPosterDetail)
        binding.textViewTitle.text = movie.name
        binding.textViewYear.text = movie.year.toString()
        binding.textViewDescription.text = movie.description
    }

    private fun observes() {
        viewModel.getReviews.observe(this) { reviewAdapter.reviewList = it }
        viewModel.getTrailers.observe(this) { trailerAdapter.trailerList = it }
        viewModel.loadReview(movie.id)
        viewModel.loadTrailer(movie.id)

        val startOn = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_on)
        val startOff = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_off)
        viewModel.getFavoriteMovie(movie.id).observe(this) {
            if (it == null) {
                binding.imageViewStar.setImageDrawable(startOff)
                binding.imageViewStar.setOnClickListener { viewModel.insertMovie(movie) }
            } else {
                binding.imageViewStar.setImageDrawable(startOn)
                binding.imageViewStar.setOnClickListener { viewModel.removeMovie(movie.id) }
            }
        }
    }

    private fun installAdapters() {
        binding.recyclerViewTrailers.adapter = trailerAdapter
        binding.recyclerViewReviews.adapter = reviewAdapter
        listener()
    }

    private fun listener() {
        trailerAdapter.onTrailerClickListener = TrailerAdapter.OnTrailerClickListener {
            val intent = Intent(ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)
        }
    }

    companion object {
        private const val MOVIE_KEY = "movie_key"
        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, movie)
            return intent
        }
    }
}