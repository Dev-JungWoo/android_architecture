package com.vincent.mymovie.data

import com.vincent.mymovie.services.ServiceModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ServiceModule::class])
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofit: Retrofit): MovieDataSource {
        return RemoteMovieDataSource(retrofit)
    }
}
