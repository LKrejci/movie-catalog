package com.example.moviecatalog.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("year") val year: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("imdbRating") val imdbRating: Float,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("cast") val cast: List<String>,
    @SerializedName("posterPath") val posterPath: String
)