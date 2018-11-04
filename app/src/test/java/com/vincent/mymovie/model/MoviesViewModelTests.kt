package com.vincent.mymovie.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.vincent.entities.Movie
import com.vincent.mymovie.BaseUnitTest
import com.vincent.mymovie.data.MovieDataSource
import com.vincent.mymovie.services.OMDBMovieService
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class MoviesViewModelTests : BaseUnitTest() {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieDataSource: MovieDataSource

    @Test
    fun viewModel_shouldReturnEmptyList() = runBlocking<Unit> {
        val emptyMovieList: List<Movie> = listOf()
        val movieService = OMDBMovieService(movieDataSource)

        given(movieDataSource.searchMovies(MOVIE_TITLE)).will { emptyMovieList }

        val viewModel = MoviesViewModel(movieService)
        viewModel.searchMovies(MOVIE_TITLE)

        verify(movieDataSource, times(1)).searchMovies(MOVIE_TITLE)
    }

    @Test
    fun viewModel_shouldReturnNonEmptyMovieList() = runBlocking<Unit> {
        var resultList: List<Movie>? = null
        val movieService = OMDBMovieService(movieDataSource)

        given(movieDataSource.searchMovies(MOVIE_TITLE)).will { SUCCESS_SEARCH_RESULT }

        val viewModel =  MoviesViewModel(movieService)

        viewModel.movies.observeForever {
            resultList = it
        }
        viewModel.searchMovies(MOVIE_TITLE)

        assert(resultList != null)
        assert(resultList === SUCCESS_SEARCH_RESULT)
        verify(movieDataSource, times(1)).searchMovies(MOVIE_TITLE)
    }
}
