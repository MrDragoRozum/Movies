package com.example.movies.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie_favorite")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("year")
    val year: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @Embedded
    @SerializedName("poster")
    val poster: Poster,
    @Embedded
    @SerializedName("rating")
    val rating: Rating
) : Serializable
