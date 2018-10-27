package com.vincent.mymovie.view.movies

import com.greentechrobotics.seedspidercontroller.history.MoviesModule
import com.vincent.mymovie.di.DataSourceModule
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [MoviesModule::class, DataSourceModule::class])
interface MoviesComponent : AndroidInjector<MoviesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MoviesFragment>()
}
