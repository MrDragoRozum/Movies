package com.example.movies.pojo

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(@SerializedName("docs") val review: List<Review>)
