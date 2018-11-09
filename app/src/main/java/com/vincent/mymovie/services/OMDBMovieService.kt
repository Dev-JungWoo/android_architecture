package com.vincent.mymovie.services

import com.vincent.entities.Movie
import com.vincent.mymovie.data.MovieDataSource
import com.vincent.usecases.service.IMovieService

class OMDBMovieService(private val dataSource: MovieDataSource) : IMovieService {
    override fun searchMovies(title: String): List<Movie>? {
        return dataSource.searchMovies(title)
    }
}