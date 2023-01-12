package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=6GJ8VPT-M24MWMB-HP9GEBM-W9W4JFW&field=rating.kp&search=7-10&sortField=votes.imdb&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=id")
    Single<TrailersResponse> loadTrailers(@Query("search") int id);

    @GET("review?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=movieId")
    Single<ReviewsResponse> loadReviews(@Query("search") int movieId);
}
