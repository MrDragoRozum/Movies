package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class TrailersResponse {
    @SerializedName("videos")
    private Trailers trailers;

    public TrailersResponse(Trailers trailers) {
        this.trailers = trailers;
    }

    public Trailers getTrailers() {
        return trailers;
    }

    @Override
    public String toString() {
        return "VideosResponse{" +
                "trailers=" + trailers +
                '}';
    }
}
