package com.vincent.mymovie.data

import com.vincent.mymovie.services.RetrofitMovieService
import com.vincent.usecases.BaseUnitTest
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.Mockito.times

@RunWith(JUnit4::class)
class RemoteMovieDataSourceTests : BaseUnitTest() {
    @Mock
    lateinit var retrofitMovieService: RetrofitMovieService

    @Test
    fun testRemoteMovieDataSourceReturnNull() = runBlocking<Unit> {
        val dataSource = RemoteMovieDataSource(retrofitMovieService)

        given(retrofitMovieService.searchMovies(API_KEY, MOVIE_TITLE)).will { null }

        assert(dataSource.searchMovies(MOVIE_TITLE) == null)
    }

    @Test
    fun testRetrofitMovieServiceSearchMoviesCalled() = runBlocking<Unit> {
        val dataSource = RemoteMovieDataSource(retrofitMovieService)

        dataSource.searchMovies(MOVIE_TITLE)

        verify(retrofitMovieService, times(1)).searchMovies("", MOVIE_TITLE)
    }
}