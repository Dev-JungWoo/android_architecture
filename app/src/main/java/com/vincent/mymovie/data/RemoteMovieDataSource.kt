package com.vincent.mymovie.data

import com.vincent.entities.Movie
import com.vincent.mymovie.services.RetrofitMovieService
import retrofit2.Retrofit

class RemoteMovieDataSource constructor(retrofit: Retrofit) : MovieDataSource {
    private val retrofitMovieService: RetrofitMovieService = retrofit.create(RetrofitMovieService::class.java)

    override suspend fun searchMovies(title: String): List<Movie>? {
        return retrofitMovieService.searchMovies(title).execute().body()
    }
}