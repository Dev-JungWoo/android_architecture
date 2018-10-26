package com.vincent.mymovie.services

import com.vincent.entities.Movie
import com.vincent.usecases.service.IMovieService

class OMDBMovieService : IMovieService {


    override suspend fun searchMovies(title: String): List<Movie> {
        return listOf()
    }

    companion object {
        val BASE_URL = "https://raw.githubusercontent.com/catchnz/android-test/master/data/"
    }
}
