package com.example.movies.api

import com.example.movies.pojo.MovieResponse
import com.example.movies.pojo.ReviewsResponse
import com.example.movies.pojo.TrailersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("movie?token=WK6EVMG-12E473Z-HEZ0B49-8MBTZRJ&field=rating.kp&search=7-10&sortField=votes.imdb&sortType=-1&limit=30")
    fun loadMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie?token=WK6EVMG-12E473Z-HEZ0B49-8MBTZRJ&field=id")
    fun loadTrailers(@Query("search") id: Int): Single<TrailersResponse>

    @GET("review?token=WK6EVMG-12E473Z-HEZ0B49-8MBTZRJ&field=movieId")
    fun loadReviews(@Query("search") movieId: Int): Single<ReviewsResponse>
}