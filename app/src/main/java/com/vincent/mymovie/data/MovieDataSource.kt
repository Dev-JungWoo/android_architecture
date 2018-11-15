package com.vincent.mymovie.data

import com.vincent.entities.Movie

interface MovieDataSource {
    fun searchMovies(title: String, page: Int = 1): List<Movie>?
}

