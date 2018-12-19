package com.vincent.mymovie.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Movie
import com.vincent.usecases.SearchMovies
import com.vincent.usecases.service.IMovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieService: IMovieService) : ViewModel(), CoroutineScope {
    private var currentTitle = ""
    private var currentPage = 1
    private var dataLoadFinished = false
    var isNewSearch = false

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    private val job = Job()
    override val coroutineContext = Dispatchers.Default + job

    fun searchMovies(title: String, page: Int = 1) {
        launch {
            isNewSearch = if (!currentTitle.contentEquals(title)) {
                initData()
                true
            } else {
                false
            }

            currentTitle = title

            SearchMovies(movieService, title, page).execute()?.let {
                if (it.isNotEmpty()) {
                    currentPage = page
                } else {
                    dataLoadFinished = true
                }

                movies.postValue(it)
            }
        }
    }

    fun loadNextPage() {
        launch {
            searchMovies(currentTitle, currentPage + 1)
        }
    }

    private fun initData() {
        currentPage = 1
        dataLoadFinished = false
    }
}
