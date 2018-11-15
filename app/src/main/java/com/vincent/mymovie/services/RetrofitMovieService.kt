package com.vincent.mymovie.services

import com.vincent.mymovie.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMovieService {
    @GET("/")
    fun searchMovies(
        @Query("apikey") apikey: String,
        @Query("s") title: String,
        @Query("page") page: Int = 0
    ): Call<MovieResult>?

    companion object {
        val BASE_URL = "http://www.omdbapi.com"
    }
}
//"http://www.omdbapi.com?apikey=${appContext.resources.getString(R.string.omdb_api_key)}&"
//${appContext.resources.getString(R.string.omdb_api_key)}