package com.vincent.mymovie

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
open class BaseUnitTest {
    companion object {
        const val API_KEY = "Test Key"
        const val MOVIE_TITLE = "Test Title"
    }

    @Before
    @Throws
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}