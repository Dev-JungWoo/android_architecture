package com.vincent.mymovie.di

import android.support.v4.app.Fragment
import com.vincent.mymovie.view.movies.MoviesFragment
import com.vincent.mymovie.view.movies.MoviesFragmentComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @dagger.android.support.FragmentKey(MoviesFragment::class)
    abstract fun bindHistoryFragmentInjectorFactory(builder: MoviesFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

//    @ContributesAndroidInjector(modules = arrayOf(MoviesFragment::class))
//    abstract fun moviesFragment(): MoviesFragment
}
