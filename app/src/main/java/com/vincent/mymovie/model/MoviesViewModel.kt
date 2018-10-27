package com.vincent.mymovie.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Movie
import com.vincent.mymovie.data.MovieDataSource
import com.vincent.usecases.service.IMovieService
import kotlinx.coroutines.experimental.launch


class MoviesViewModel(val dataSource: MovieDataSource) : ViewModel(), IMovieService {
    private lateinit var movies: MutableLiveData<List<Movie>>

    fun getMovies(title: String): LiveData<List<Movie>> {
        launch {
            if (!::movies.isInitialized) {
                movies = MutableLiveData()
            }

            movies.value = searchMovies(title)
        }

        return movies
    }

    override suspend fun searchMovies(title: String): List<Movie>? {
        return dataSource.searchMovies(title)
    }
}