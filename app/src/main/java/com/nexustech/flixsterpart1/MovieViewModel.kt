package com.nexustech.flixsterpart1


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    // LiveData for storing movies
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    // LiveData for managing loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData for error handling
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    // Fetch movies from the API
    fun fetchMovies(apiKey: String) {
        _isLoading.value = true

        ApiClient.movieApiService.getNowPlayingMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    Log.d("MovieViewModel", "Response: ${response.body()}")
                    _movies.value = response.body()?.results
                } else {
                    Log.e("MovieViewModel", "Error: ${response.errorBody()?.string()}")
                    _error.value = "Error: ${response.errorBody()}"
                }
            }


            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.message
            }
        })
    }
}
