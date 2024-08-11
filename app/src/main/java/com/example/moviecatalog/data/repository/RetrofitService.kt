package com.example.moviecatalog.data.repository

import com.example.moviecatalog.data.model.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    @GET("shows/top")
    @Headers(
        "x-rapidapi-key: c297bc89e1msha2364a1816a1026p1d2b03jsn3c285cd51529",
        "x-rapidapi-host: streaming-availability.p.rapidapi.com"
    )
    fun getAllMovies(
        @Query("country") country: String,
        @Query("service") service: String,
        @Query("showType") showType: String
    ): Call<List<Movie>>

    companion object {
        private val logging: HttpLoggingInterceptor
            get() = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        private val httpClient: OkHttpClient
            get() = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        private var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://streaming-availability.p.rapidapi.com/")
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}