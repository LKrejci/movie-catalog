package com.example.moviecatalog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalog.R
import com.example.moviecatalog.data.repository.Repository
import com.example.moviecatalog.data.repository.RetrofitService
import com.example.moviecatalog.databinding.MovieListActivityBinding
import com.example.moviecatalog.ui.viewModel.MovieViewModel
import com.example.moviecatalog.ui.viewModel.ViewModelFactory

class MainActivity : ComponentActivity() {
    private val TAG = "MovieListActivity"
    private lateinit var binding: MovieListActivityBinding

    private lateinit var viewModel: MovieViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "LUCAS - onCreate()")

        setContentView(R.layout.movie_list_activity)
        binding = MovieListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(Repository(retrofitService))
        )[MovieViewModel::class.java]

        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(this) {
            Log.d(TAG, "onCreate: $it")
            Log.d(TAG, "LUCAS - movie list: ${it.size}")
            adapter.setMovieList(it)
        }

        viewModel.errorMessage.observe(this) {

        }

        viewModel.getAllMovie()
    }
}
