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

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(JUnit4::class)
class MoviesViewModelUnitTest : BaseUnitTest() {
    companion object {
        private const val MOVIE_TITLE = "Test Title"
    }

    @Mock
    lateinit var movieDataSource: MovieDataSource

    @Test
    fun testSearchMovies() = runBlocking<Unit> {
        val emptyMovieList: List<Movie> = listOf()
        val movieService = OMDBMovieService(movieDataSource)

        given(movieDataSource.searchMovies(MOVIE_TITLE)).will { emptyMovieList }

        val viewModel =  MoviesViewModel(movieService)
        viewModel.searchMovies(MOVIE_TITLE)

        verify(movieDataSource).searchMovies(MOVIE_TITLE)
    }
}
