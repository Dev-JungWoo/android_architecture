package com.vincent.mymovie.data

import com.vincent.entities.Movie

interface MovieDataSource {
    suspend fun searchMovies(title: String): List<Movie>?
}

