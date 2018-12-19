package com.vincent.mymovie.view.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.vincent.entities.Movie
import com.vincent.mymovie.R
import com.vincent.mymovie.model.MoviesViewModel
import com.vincent.mymovie.model.MoviesViewModelFactory
import com.vincent.mymovie.services.OMDBMovieService
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MovieListFragment : Fragment(), IMovieSelectListener {

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
        movieListRecyclerView.adapter = MovieListAdapter(this)
        movieListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

//                Log.d(TAG, "onScrollStateChanged newState = $newState")
                if (!recyclerView.canScrollVertically(1) && newState == SCROLL_STATE_IDLE) {
                    moviesViewModel.loadNextPage()
                }
            }
        })

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

        val existingMovieList = (movieListRecyclerView.adapter as MovieListAdapter).movieList

        if (moviesViewModel.isNewSearch) {
            existingMovieList.clear()
        }

        movieList?.let {
            val startCount = existingMovieList.size

            Log.d(TAG, "startCount = $startCount, count = ${it.size}")

            existingMovieList.addAll(it)

            if (moviesViewModel.isNewSearch) {
                movieListRecyclerView.adapter?.notifyDataSetChanged()
            } else {
                movieListRecyclerView.adapter?.notifyItemRangeInserted(startCount, it.size)
            }
        }

        noMovieTextView.visibility = if (existingMovieList.isEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
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

    override fun onSelect(movie: Movie) {
    }


    private fun onSearchSubmit(query: String) {
        if (query.isNotEmpty()) {
            moviesViewModel.searchMovies(query)
        }
    }
}
