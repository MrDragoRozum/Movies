package com.example.movies;

import static android.content.Intent.ACTION_VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView imageViewPosterDetail;
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
        viewModel = new ViewModelProvider(this ).get(MovieDetailViewModel.class);

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

    }

    private void initView() {
        imageViewPosterDetail = findViewById(R.id.imageViewPosterDetail);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_KEY, movie);
        return intent;
    }
}