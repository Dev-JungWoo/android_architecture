package com.vincent.mymovie.modules

import com.vincent.mymovie.data.MovieDataSource
import com.vincent.mymovie.data.RemoteMovieDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofit: Retrofit): MovieDataSource {
        return RemoteMovieDataSource(retrofit)
    }
}
