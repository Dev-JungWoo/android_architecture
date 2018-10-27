package com.greentechrobotics.seedspidercontroller.history

import com.vincent.mymovie.view.movies.MoviesFragment
import com.vincent.mymovie.view.movies.MoviesFragmentComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


@Module(subcomponents = [MoviesFragmentComponent::class])
abstract class MoviesFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(MoviesFragment::class)
    internal abstract fun bindYourFragmentInjectorFactory(builder: MoviesFragmentComponent.Builder): AndroidInjector.Factory<*>
}
