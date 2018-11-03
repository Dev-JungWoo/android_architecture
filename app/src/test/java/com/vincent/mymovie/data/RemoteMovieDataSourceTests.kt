package com.vincent.mymovie.data

import com.vincent.mymovie.BaseUnitTest
import com.vincent.mymovie.services.RetrofitMovieService
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock

@RunWith(JUnit4::class)
class RemoteMovieDataSourceTests : BaseUnitTest() {
    @Mock
    lateinit var retrofitMovieService: RetrofitMovieService

    @Test
    fun testRemoteMovieDataSource() {
        val dataSource = RemoteMovieDataSource(retrofitMovieService)

        given(retrofitMovieService.searchMovies(API_KEY, MOVIE_TITLE)).will {  }


//        dataSource.searchMovies()
    }

//    @Test
//    fun testDataSourceSearchMoviesCalledByViewModel() = runBlocking<Unit> {
//        val emptyMovieList: List<Movie> = listOf()
//        val movieService = OMDBMovieService(movieDataSource)
//
//        BDDMockito.given(movieDataSource.searchMovies(MoviesViewModelTests.MOVIE_TITLE)).will { emptyMovieList }
//
//        val viewModel =  MoviesViewModel(movieService)
//        viewModel.searchMovies(MoviesViewModelTests.MOVIE_TITLE)
//
//        Mockito.verify(movieDataSource).searchMovies(MoviesViewModelTests.MOVIE_TITLE)
//    }

}