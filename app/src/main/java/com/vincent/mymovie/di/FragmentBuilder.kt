package com.vincent.mymovie.di

import android.support.v4.app.Fragment
import com.vincent.mymovie.view.movies.MoviesComponent
import com.vincent.mymovie.view.movies.MoviesFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @dagger.android.support.FragmentKey(MoviesFragment::class)
    abstract fun bindHistoryFragmentInjectorFactory(builder: MoviesComponent.Builder): AndroidInjector.Factory<out Fragment>

//    @ContributesAndroidInjector(modules = arrayOf(MoviesFragment::class))
//    abstract fun moviesFragment(): MoviesFragment
}
