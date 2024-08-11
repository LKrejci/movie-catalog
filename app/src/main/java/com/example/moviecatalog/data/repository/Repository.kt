package com.example.moviecatalog.data.repository

class Repository(private val retrofitService: RetrofitService) {
    fun getAllMovies() =
        retrofitService.getAllMovies(country = "us", service = "netflix", showType = "series")
}