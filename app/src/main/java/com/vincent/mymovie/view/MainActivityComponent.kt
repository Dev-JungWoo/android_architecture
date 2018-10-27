package com.vincent.mymovie.di

import com.greentechrobotics.seedspidercontroller.history.MoviesFragmentModule
import com.vincent.mymovie.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

//@Subcomponent(modules = [MainActivityModule::class, FragmentBuilder::class])
@Subcomponent(modules = [MoviesFragmentModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
