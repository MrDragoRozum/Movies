package com.example.movies;

import static android.content.Intent.ACTION_VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView imageViewPosterDetail;
    private ImageView imageViewStar;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private static final String MOVIE_KEY = "movie_key";
    private static final String TAG = "MovieDetailActivity";

    private MovieDetailViewModel viewModel;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initView();
        Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_KEY);
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailers.setAdapter(trailerAdapter);
        viewModel.getTrailers().observe(this,
                trailers -> trailerAdapter.setTrailerList(trailers));
        viewModel.loadTrailer(movie.getId());

        reviewAdapter = new ReviewAdapter();
        recyclerViewReviews.setAdapter(reviewAdapter);
        viewModel.getReviews().observe(this, reviews -> reviewAdapter.setReviewList(reviews));
        viewModel.loadReview(movie.getId());


        Glide.with(this).load(movie.getPoster().getUrl()).into(imageViewPosterDetail);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        trailerAdapter.setOnTrailerClickListener(trailer -> {
            Intent intent = new Intent(ACTION_VIEW);
            intent.setData(Uri.parse(trailer.getUrl()));
            startActivity(intent);
        });

        Drawable startOn = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_on);
        Drawable startOff = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_off);
        viewModel.getFavoriteMovie(movie.getId()).observe(this, favoriteMovie -> {
            if (favoriteMovie == null) {
                imageViewStar.setImageDrawable(startOff);
                imageViewStar.setOnClickListener(l -> viewModel.insertMovie(movie));
            } else {
                imageViewStar.setImageDrawable(startOn);
                imageViewStar.setOnClickListener(l -> viewModel.removeMovie(movie.getId()));
            }
        });
    }

    private void initView() {
        imageViewPosterDetail = findViewById(R.id.imageViewPosterDetail);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_KEY, movie);
        return intent;
    }
}