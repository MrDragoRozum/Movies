package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=rating.kp&search=7-10&sortField=votes.imdb&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}
