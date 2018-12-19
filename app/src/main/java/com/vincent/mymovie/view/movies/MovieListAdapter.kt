package com.vincent.mymovie.view.movies

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vincent.entities.Movie
import com.vincent.mymovie.R
import kotlinx.android.synthetic.main.card_movie.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL


class MovieListAdapter(private val movieSelectListener: IMovieSelectListener) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    val TAG = javaClass.simpleName

    var movieList: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.card_movie, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        val cardView = holder.cardView

        holder.imageLoadingJob?.cancel()
        holder.cardView.posterImageView.setImageResource(R.drawable.baseline_loop_black_48)

        if (!movie.imageUrl.contentEquals("N/A")) {
            holder.imageLoadingJob = GlobalScope.launch(Dispatchers.IO) {
                loadImage(movie.imageUrl)?.let {
                    GlobalScope.launch(Dispatchers.Main) { cardView.posterImageView.setImageBitmap(it) }
                }
            }
        }

        cardView.titleTextView.text = movie.title
        cardView.yearTextView.text = movie.year
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.imageLoadingJob?.cancel()

        holder.cardView.posterImageView.setImageResource(R.drawable.baseline_loop_black_48)
    }

    private fun loadImage(url: String): Bitmap? {
        val inputStream: InputStream?
        var bitmap: Bitmap? = null
        try {
            inputStream = URL(url).openStream()
            if (inputStream != null) {
                bitmap = BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error while loading image", e)
        }

        return bitmap
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        var imageLoadingJob: Job? = null
    }
}
