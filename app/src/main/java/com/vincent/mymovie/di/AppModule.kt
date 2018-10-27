package com.vincent.mymovie.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    // TODO: All modules will use application context which is not ideal.
    @Binds
    abstract fun bindContext(application: Application): Context
}
