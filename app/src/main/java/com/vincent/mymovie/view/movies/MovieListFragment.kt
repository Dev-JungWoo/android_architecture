package com.vincent.mymovie.view.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.Toast
import com.vincent.entities.Movie
import com.vincent.mymovie.R
import com.vincent.mymovie.model.MoviesViewModel
import com.vincent.mymovie.model.MoviesViewModelFactory
import com.vincent.mymovie.services.OMDBMovieService
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class MovieListFragment : Fragment(), IMoviesView {
    private val TAG = javaClass.simpleName

    private lateinit var moviesViewModel: MoviesViewModel

    @Inject
    lateinit var omdbMovieService: OMDBMovieService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        moviesViewModel = ViewModelProviders.of(activity!!, MoviesViewModelFactory(omdbMovieService)).get(MoviesViewModel::class.java)

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_movies, container, false)!!
    }

    override fun onStart() {
        super.onStart()

        movieListRecyclerView.layoutManager = LinearLayoutManager(activity)
        movieListRecyclerView.adapter = MovieListAdapter(moviesViewModel.movies.value ?: listOf(), this)

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
        if (movieList == null || movieList.isEmpty()) {
            noMovieTextView.visibility = View.VISIBLE
            (movieListRecyclerView.adapter as MovieListAdapter).movieList = listOf()
            Toast.makeText(activity, "Movie not found", Toast.LENGTH_SHORT).show()
        } else {
            noMovieTextView.visibility = View.GONE
            (movieListRecyclerView.adapter as MovieListAdapter).movieList = movieList
        }

        movieListRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.searchView)
        val searchView = searchItem.actionView as SearchView
        searchView.isIconified = false
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                Log.d(TAG, "onQueryTextSubmit")

                onSearchSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun onSearchSubmit(query: String) {
        if (query.isNotEmpty()) {
            launch(UI) { moviesViewModel.searchMovies(query) }
        }
    }
}
