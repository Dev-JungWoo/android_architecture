package com.vincent.mymovie.di

import com.vincent.mymovie.view.movies.MoviesComponent
import dagger.Module

@Module(subcomponents = [MoviesComponent::class])
class MainActivityModule
