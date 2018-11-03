package com.vincent.mymovie.model

import com.vincent.entities.Movie
import com.vincent.mymovie.BaseUnitTest
import com.vincent.mymovie.data.MovieDataSource
import com.vincent.mymovie.services.OMDBMovieService
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class MoviesViewModelTests : BaseUnitTest() {
    @Mock
    lateinit var movieDataSource: MovieDataSource

    @Test
    fun testDataSourceSearchMoviesCalledByViewModel() = runBlocking<Unit> {
        val emptyMovieList: List<Movie> = listOf()
        val movieService = OMDBMovieService(movieDataSource)

        given(movieDataSource.searchMovies(MOVIE_TITLE)).will { emptyMovieList }

        val viewModel =  MoviesViewModel(movieService)
        viewModel.searchMovies(MOVIE_TITLE)

        verify(movieDataSource).searchMovies(MOVIE_TITLE)
    }
}
