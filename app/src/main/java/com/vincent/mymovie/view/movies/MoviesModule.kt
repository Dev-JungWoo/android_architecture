package com.greentechrobotics.seedspidercontroller.history

import dagger.Module
import dagger.Provides

@Module
class MoviesModule {
    @Provides
    fun providePresenter(dataManager: DataManager<History>): HistoryContract.Presenter {
        return HistoryPresenter(dataManager)
    }
}
