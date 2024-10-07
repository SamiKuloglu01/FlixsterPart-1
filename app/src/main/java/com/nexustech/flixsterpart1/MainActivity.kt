package com.nexustech.flixsterpart1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewMovies)
        progressBar = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        movieViewModel.movies.observe(this) { movieList ->
            if (movieList != null && movieList.isNotEmpty()) {
                val movieAdapter = MovieAdapter(movieList)
                recyclerView.adapter = movieAdapter
                recyclerView.visibility = View.VISIBLE
            }
        }

        movieViewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        movieViewModel.fetchMovies("a07e22bc18f5cb106bfe4cc1f83ad8ed")
    }
}
