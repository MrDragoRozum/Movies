package com.example.movies.api

import com.example.movies.pojo.MovieResponse
import com.example.movies.pojo.ReviewsResponse
import com.example.movies.pojo.TrailersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("movie?token=APS9HWG-F124CR7-MTSS4FC-KZQHDNR&field=rating.kp&search=7-10&sortField=votes.imdb&sortType=-1&limit=30")
    fun loadMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie?token=APS9HWG-F124CR7-MTSS4FC-KZQHDNR&field=id")
    fun loadTrailers(@Query("search") id: Int): Single<TrailersResponse>
    @GET("review?token=APS9HWG-F124CR7-MTSS4FC-KZQHDNR&field=movieId")
    fun loadReviews(movieId: Int): Single<ReviewsResponse>
}