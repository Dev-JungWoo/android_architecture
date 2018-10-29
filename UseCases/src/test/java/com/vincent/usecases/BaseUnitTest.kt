package com.vincent.usecases

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
open class BaseUnitTest {
    @Before
    @Throws
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}