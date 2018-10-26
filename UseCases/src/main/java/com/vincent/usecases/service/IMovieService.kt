package com.vincent.usecases.service

import com.vincent.entities.Movie

interface IMovieService {
    suspend fun searchMovies(title: String): List<Movie>
}