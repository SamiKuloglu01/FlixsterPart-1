package com.nexustech.flixsterpart1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewMovies)
        progressBar = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchMovies()
    }

    private fun fetchMovies() {
        progressBar.visibility = View.VISIBLE

        ApiClient.movieApiService.getNowPlayingMovies("a07e22bc18f5cb106bfe4cc1f83ad8ed").enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    response.body()?.let {
                        movieAdapter = MovieAdapter(it.results)
                        recyclerView.adapter = movieAdapter
                        recyclerView.visibility = View.VISIBLE
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Hide the ProgressBar on failure
                progressBar.visibility = View.GONE
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}
