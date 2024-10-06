package com.nexustech.flixsterpart1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Define the API service interface
interface MovieApiService {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String): Call<MovieResponse>
}
