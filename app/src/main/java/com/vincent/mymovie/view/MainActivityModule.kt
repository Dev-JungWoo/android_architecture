package com.vincent.mymovie.di

import dagger.Module

@Module(subcomponents = [MoviesComponent::class, MySeedsComponent::class])
class MainActivityModule
