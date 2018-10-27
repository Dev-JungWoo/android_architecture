package com.vincent.mymovie.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vincent.usecases.service.IMovieService

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val service: IMovieService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(service) as T
    }
}