package com.vincent.mymovie.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Movie
import com.vincent.usecases.SearchMovies
import com.vincent.usecases.service.IMovieService
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch


class MoviesViewModel(private val movieService: IMovieService) : ViewModel() {
    var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun searchMovies(title: String): LiveData<List<Movie>> {
        launch(UI) {
            val searchWork = async { SearchMovies(movieService, title).execute() }
            movies.value = searchWork.await()
        }

        return movies
    }
}
