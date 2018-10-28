package com.vincent.mymovie.data

import android.util.Log
import com.vincent.entities.Movie
import com.vincent.mymovie.Constants
import com.vincent.mymovie.services.RetrofitMovieService
import retrofit2.Retrofit

class RemoteMovieDataSource constructor(retrofit: Retrofit) : MovieDataSource {
    private val TAG = javaClass.simpleName
    private val retrofitMovieService: RetrofitMovieService = retrofit.create(RetrofitMovieService::class.java)

    override suspend fun searchMovies(title: String): List<Movie>? {
        val list = mutableListOf<Movie>()
        val response = retrofitMovieService.searchMovies(Constants.OMDB_API_KEY, title).execute()

        if (!response.isSuccessful) {
            Log.d(TAG, "$response")
            return null
        }

        val content = response.body()!!

        if (content.response.contentEquals("True")) {
            content.search?.forEach {
                list.add(Movie(it.title!!, it.year!!, it.poster!!))
            }
        }

        return list
    }
}
