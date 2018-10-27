package com.vincent.mymovie.services

import com.vincent.entities.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMovieService {
    @GET("/s={title}")
    fun searchMovies(@Path("title") title: String): Call<List<Movie>>

    companion object {
        // ex> http://www.omdbapi.com/?s=Batman&page=2
        val BASE_URL = "http://www.omdbapi.com"
    }
}
