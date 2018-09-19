package com.vincent.usecases

import com.vincent.entities.Movie
import com.vincent.usecases.service.IMovieService

class GetLatestMovies(private val service: IMovieService) : Interactor<List<Movie>> {

    override fun execute(): List<Movie> {
        return service.getLatestMovies()
    }
}
