package com.vincent.usecases

import com.vincent.entities.Movie
import com.vincent.usecases.base.Interactor
import com.vincent.usecases.service.IMovieService

open class SearchMovies(private val service: IMovieService, private val title: String, private val page: Int = 1) : Interactor<List<Movie>> {
    override fun execute(): List<Movie>? {
        return service.searchMovies(title, page)
    }
}
