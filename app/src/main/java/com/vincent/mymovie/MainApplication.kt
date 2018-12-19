package com.vincent.mymovie

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import com.vincent.mymovie.di.DaggerAppComponent
import com.vincent.mymovie.services.RetrofitMovieService
import com.vincent.mymovie.services.ServiceModule
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
                .serviceModule(ServiceModule(RetrofitMovieService.BASE_URL))
                .build()
                .inject(this)

        Constants.OMDB_API_KEY = "161ea2d9"
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}