package com.vincent.mymovie.view.movies

import com.vincent.entities.Movie

interface IMovieSelectListener {
    fun onSelect(movie: Movie)
}