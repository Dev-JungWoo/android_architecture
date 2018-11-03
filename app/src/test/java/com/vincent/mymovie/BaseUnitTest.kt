package com.vincent.mymovie

import com.vincent.entities.Movie
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
open class BaseUnitTest {
    companion object {
        const val API_KEY = "Test Key"
        const val MOVIE_TITLE = "Test Title"

        val SUCCESS_SEARCH_RESULT = listOf(
                Movie("Test Movie 1", "2001", "https://m.media-amazon.com/images/M/MV5BMGU5OWEwZDItNmNkMC00NzZmLTk1YTctNzVhZTJjM2NlZTVmXkEyXkFqcGdeQXVyMTMxODk2OTU"),
                Movie("Test Movie 2", "2002", "https://m.media-amazon.com/images/M/MV5BMGU5OWEwZDItNmNkMC00NzZmLTk1YTctNzVhZTJjM2NlZTVmXkEyXkFqcGdeQXVyMTMxODk2OTU"),
                Movie("Test Movie 3", "2003", "https://m.media-amazon.com/images/M/MV5BMGU5OWEwZDItNmNkMC00NzZmLTk1YTctNzVhZTJjM2NlZTVmXkEyXkFqcGdeQXVyMTMxODk2OTU")
        )
    }

//    {"Search":[{"Title":"City of God","Year":"2002","imdbID":"tt0317248","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMGU5OWEwZDItNmNkMC00NzZmLTk1YTctNzVhZTJjM2NlZTVmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"},{"Title":"Only God Forgives","Year":"2013","imdbID":"tt1602613","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMzE5NzcxMTk5NF5BMl5BanBnXkFtZTcwNjE2MDg2OQ@@._V1_SX300.jpg"},{"Title":"God Bless America","Year":"2011","imdbID":"tt1912398","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTQwMTc1MzA4NF5BMl5BanBnXkFtZTcwNzQwMTgzNw@@._V1_SX300.jpg"},{"Title":"Aguirre, the Wrath of God","Year":"1972","imdbID":"tt0068182","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMjRkY2VhYzMtZWQyNS00OTY2LWE5NTAtYjlhNmQyYzE5MmUxXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"},{"Title":"Son of God","Year":"2014","imdbID":"tt3210686","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMjA3NjQ2ODYwN15BMl5BanBnXkFtZTgwNTQ0NDc2MDE@._V1_SX300.jpg"},{"Title":"Armour of God 2: Operation Condor","Year":"1991","imdbID":"tt0099558","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BNjEyMTk4ZDgtNjUyZi00OTg0LWFkMWEtNjk0ZWE5N2QwMmZmXkEyXkFqcGdeQXVyNjU5MjcxOTg@._V1_SX300.jpg"},{"Title":"Armour of God","Year":"1986","imdbID":"tt0091431","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTFkMmMxNTMtMDRkYy00OWYzLWIxM2QtN2IzZjBkNThmZTVkXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg"},{"Title":"White God","Year":"2014","imdbID":"tt2844798","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BNjk4MzkyOTQwNF5BMl5BanBnXkFtZTgwODE5MTk1MzE@._V1_SX300.jpg"},{"Title":"Children of a Lesser God","Year":"1986","imdbID":"tt0090830","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BN2VjN2ViNTItMWFhNS00MTdjLTk0MTEtYTA5MzQ5OThkMjgwXkEyXkFqcGdeQXVyMTA0MjU0Ng@@._V1_SX300.jpg"},{"Title":"Astérix and Obélix: God Save Britannia","Year":"2012","imdbID":"tt1597522","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTQwMzUxNzAwMF5BMl5BanBnXkFtZTcwMjg4NzE1OQ@@._V1_SX300.jpg"}],"totalResults":"1716","Response":"True"}

    @Before
    @Throws
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}