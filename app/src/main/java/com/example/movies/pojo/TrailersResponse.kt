package com.example.movies.pojo

import com.google.gson.annotations.SerializedName

data class TrailersResponse(@SerializedName("videos") val trailers: Trailers)
