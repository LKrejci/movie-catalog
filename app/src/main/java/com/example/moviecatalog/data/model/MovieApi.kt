package com.example.moviecatalog.data.model

import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("movieList.json")
    fun getAllMovies(): Call<List<Movie>>
}