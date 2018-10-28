package com.vincent.mymovie.di

import com.vincent.mymovie.MainActivity
import com.vincent.mymovie.view.movies.MoviesFragment
import com.vincent.mymovie.view.movies.MoviesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MoviesFragmentModule::class])
    abstract fun bindMoviesFragment(): MoviesFragment
}