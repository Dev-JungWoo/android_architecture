package com.vincent.mymovie.data

import android.util.Log
import com.vincent.entities.Movie
import com.vincent.mymovie.Constants
import com.vincent.mymovie.model.MovieResult
import com.vincent.mymovie.services.RetrofitMovieService
import retrofit2.Response
import java.io.IOException

class RemoteMovieDataSource constructor(private val retrofitMovieService: RetrofitMovieService) : MovieDataSource {
    private val TAG = javaClass.simpleName

    override fun searchMovies(title: String): List<Movie>? {
        val list = mutableListOf<Movie>()

        var response: Response<MovieResult>? = null

        try {
            response = retrofitMovieService.searchMovies(Constants.OMDB_API_KEY, title)?.execute()
        } catch (e: IOException) {
            Log.e(TAG, "searchMovies error", e)
        }

        if (response == null || !response.isSuccessful) {
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
