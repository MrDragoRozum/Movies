package com.example.movies.pojo

import com.google.gson.annotations.SerializedName

data class Trailers(@SerializedName("trailers") val trailerList: List<Trailer>)
