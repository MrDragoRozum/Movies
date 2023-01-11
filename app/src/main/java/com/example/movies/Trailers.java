package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailers {
    @SerializedName("trailers")
    private List<Trailer> trailerList;

    public Trailers(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    @Override
    public String toString() {
        return "Trailers{" +
                "trailerList=" + trailerList +
                '}';
    }
}
