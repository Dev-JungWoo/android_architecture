package com.vincent.usecases

import com.vincent.usecases.service.IMovieService
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class SearchMoviesTests : BaseUnitTest() {
    @Mock
    lateinit var movieService: IMovieService


    @Test
    fun searchMovies_shouldReturnCorrectResult() {
        given(movieService.searchMovies(MOVIE_TITLE)).will { SUCCESS_SEARCH_RESULT }

        val result = SearchMovies(movieService, MOVIE_TITLE).execute()

        Mockito.verify(movieService, times(1)).searchMovies(MOVIE_TITLE)
        assert(result == SUCCESS_SEARCH_RESULT)
    }

    @Test
    fun searchMovies_shouldReturnNull() = {
        given(movieService.searchMovies(MOVIE_TITLE)).will { null }

        val result = SearchMovies(movieService, MOVIE_TITLE).execute()

        Mockito.verify(movieService, times(1)).searchMovies(MOVIE_TITLE)
        assert(result == null)
    }
}
