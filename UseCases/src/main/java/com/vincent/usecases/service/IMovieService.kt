package com.vincent.usecases.service

import com.vincent.entities.Movie

interface IMovieService {
    fun getLatestMovies(): List<Movie>
}