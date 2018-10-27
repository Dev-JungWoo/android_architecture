package com.vincent.mymovie

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import com.vincent.mymovie.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainApplication : Application() , HasActivityInjector {
    val TAG = javaClass.simpleName

    init {
        Log.d(TAG, "MainApplication initialized")
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

        Constants.OMDB_API_KEY = getString(R.string.omdb_api_key)
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}