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

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    suspend fun searchMovies(title: String, page: Int = 1) {
        if (isNewSearch(title)) { initData() }

        currentTitle = title

        val searchWork = async { SearchMovies(movieService, title, page).execute() }
        val result = searchWork.await()

        result?.let {
            if (result.isNotEmpty()) {
                movies.postValue(result)
                currentPage = page
            } else {
                dataLoadFinished = true
            }
        }
    }

    suspend fun loadNextPage() {
        searchMovies(currentTitle, currentPage + 1)
    }

    private fun initData() {
        currentPage = 1
        dataLoadFinished = false
    }

    private fun isNewSearch(title: String): Boolean {
        return !currentTitle.contentEquals(title)
    }
}
