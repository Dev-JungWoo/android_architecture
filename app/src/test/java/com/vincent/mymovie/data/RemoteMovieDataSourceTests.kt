package com.vincent.mymovie.data

import com.vincent.mymovie.BaseUnitTest
import com.vincent.mymovie.services.RetrofitMovieService
import kotlinx.coroutines.experimental.runBlocking
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
    fun testRemoteMovieDataSourceReturnNull() = runBlocking {
        val dataSource = RemoteMovieDataSource(retrofitMovieService)

        given(retrofitMovieService.searchMovies(API_KEY, MOVIE_TITLE)).will { null }

        assert(dataSource.searchMovies(MOVIE_TITLE) == null)
    }
}