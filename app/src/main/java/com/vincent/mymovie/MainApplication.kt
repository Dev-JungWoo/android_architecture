package com.vincent.mymovie

import android.app.Application
import android.util.Log

class MainApplication : Application() {
    val TAG = javaClass.simpleName

    init {
        Log.d(TAG, "MainApplication initialized")
    }

    override fun onCreate() {
        super.onCreate()

        Constants.OMDB_API_KEY = getString(R.string.omdb_api_key)
    }
}