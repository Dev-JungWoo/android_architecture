package com.vincent.mymovie.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Movie
import com.vincent.usecases.SearchMovies
import com.vincent.usecases.service.IMovieService
import kotlinx.coroutines.experimental.async


class MoviesViewModel(private val movieService: IMovieService) : ViewModel() {
    private var currentTitle = ""
    private var currentPage = 1
    private var dataLoadFinished = false
    var isNewSearch = false

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    suspend fun searchMovies(title: String, page: Int = 1) {
        isNewSearch = if (!currentTitle.contentEquals(title)) {
            initData()
            true
        } else {
            false
        }

        currentTitle = title

        val searchWork = async { SearchMovies(movieService, title, page).execute() }
        val result = searchWork.await()

        result?.let {
            if (result.isNotEmpty()) {
                currentPage = page
            } else {
                dataLoadFinished = true
            }

            movies.postValue(result)
        }
    }

    suspend fun loadNextPage() {
        searchMovies(currentTitle, currentPage + 1)
    }

    private fun initData() {
        currentPage = 1
        dataLoadFinished = false
    }
}
