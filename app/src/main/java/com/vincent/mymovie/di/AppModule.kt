package com.vincent.mymovie.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application) = application.applicationContext

//    @Singleton
//    @Provides
//    fun providesCommonHelloService() = CommonHelloService()
}
