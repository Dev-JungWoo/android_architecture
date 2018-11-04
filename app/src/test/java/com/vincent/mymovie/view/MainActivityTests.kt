package com.vincent.mymovie.view

import com.vincent.mymovie.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTests {
    @Test
    fun checkInitialTitle() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assert(activity.title == "Home")
    }
}