package com.vincent.mymovie.di

import com.vincent.mymovie.data.MovieDataSource
import com.vincent.mymovie.data.RemoteMovieDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataSourceModule {
    @Provides
    fun providesRemoteMovieDataSource(retrofit: Retrofit): MovieDataSource {
        return RemoteMovieDataSource(retrofit)
    }


//    @Provides
//    fun provideHistoryDataManager(context: Context): DataManager<History> {
//        return DataManager(context, "history.db")
//    }
//
//    @Provides
//    fun provideMySeedsDataManager(context: Context): DataManager<Seed> {
//        return DataManager(context, "myseeds.db")
//    }
}