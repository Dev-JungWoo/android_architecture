package com.vincent.mymovie.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Movie
import com.vincent.usecases.SearchMovies
import com.vincent.usecases.service.IMovieService
import kotlinx.coroutines.experimental.launch


class MoviesViewModel(private val movieService: IMovieService) : ViewModel() {
    private lateinit var movies: MutableLiveData<List<Movie>>

    fun searchMovies(title: String): LiveData<List<Movie>> {
        if (!::movies.isInitialized) {
            movies = MutableLiveData()
        }

        launch {
            movies.value = SearchMovies(movieService, title).execute()
        }

        return movies
    }
}