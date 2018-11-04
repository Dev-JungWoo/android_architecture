package com.vincent.usecases

import com.vincent.entities.Movie
import com.vincent.usecases.base.SuspendInteractor
import com.vincent.usecases.service.IMovieService

open class SearchMovies(private val service: IMovieService, private val title: String) : SuspendInteractor<List<Movie>> {
    override suspend fun execute(): List<Movie>? {
        return service.searchMovies(title)
    }
}
