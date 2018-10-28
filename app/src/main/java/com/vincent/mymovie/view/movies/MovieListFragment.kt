package com.vincent.mymovie.view.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.vincent.entities.Movie
import com.vincent.mymovie.R
import com.vincent.mymovie.model.MoviesViewModel
import com.vincent.mymovie.model.MoviesViewModelFactory
import com.vincent.mymovie.services.OMDBMovieService
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MovieListFragment : Fragment(), IMoviesView {
    private val TAG = javaClass.simpleName

    private lateinit var moviesViewModel: MoviesViewModel

    @Inject
    lateinit var omdbMovieService: OMDBMovieService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        moviesViewModel = ViewModelProviders.of(activity!!, MoviesViewModelFactory(omdbMovieService)).get(MoviesViewModel::class.java)

        return inflater.inflate(R.layout.fragment_movies, container, false)!!
    }

    override fun onStart() {
        super.onStart()

        movieListRecyclerView.layoutManager = LinearLayoutManager(activity)
        searchButton.setOnClickListener { onSearchButtonClicked() }
        updateUI(moviesViewModel.movies.value)
        moviesViewModel.movies.observe(this, Observer {
            updateUI(it)
        })
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun updateUI(movieList: List<Movie>?) {
        Log.d(TAG, "updateUI, movieList = $movieList")
        if (movieList == null) {
            noMovieTextView.visibility = View.VISIBLE
        } else {
            noMovieTextView.visibility = View.GONE

            movieListRecyclerView.adapter = MovieListAdapter(movieList, this)
            movieListRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun onSearchButtonClicked() {
        val searchText = searchEditText.text.toString()
        val inputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(movieListFragmentLayout.windowToken, 0)

        if (searchText.isNotEmpty()) {
            moviesViewModel.searchMovies(searchText)
        }
    }
}
