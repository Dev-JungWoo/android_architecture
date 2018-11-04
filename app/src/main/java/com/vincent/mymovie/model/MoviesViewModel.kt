package com.vincent.mymovie.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Movie
import com.vincent.usecases.SearchMovies
import com.vincent.usecases.service.IMovieService
import kotlinx.coroutines.experimental.async


class MoviesViewModel(private val movieService: IMovieService) : ViewModel() {
    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    suspend fun searchMovies(title: String) {
        val searchWork = async { SearchMovies(movieService, title).execute() }
        val result = searchWork.await()

        if (result != null && result.isNotEmpty()) {
            movies.value = result
        }
    }
}
