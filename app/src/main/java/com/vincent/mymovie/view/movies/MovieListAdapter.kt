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
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
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
            launch(UI) {
                holder.imageLoadingJob = async { loadImage(movie.imageUrl) }
                holder.imageLoadingJob?.await()?.let { cardView.posterImageView.setImageBitmap(it) }
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
            Log.e(TAG, "Error while loading image")
        }

        return bitmap
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        var imageLoadingJob: Deferred<Bitmap?>? = null
    }

    /**
     * Replaced by coroutines.
    **/
//    inner class DownloadImage(private var bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {
//
//        override fun doInBackground(vararg urls: String): Bitmap? {
//            val url = urls[0]
//
//            Log.d(TAG, "urldisplay=$url")
//
//            var bitmap: Bitmap? = null
//            try {
//                val inputStream = java.net.URL(url).openStream()
//                bitmap = BitmapFactory.decodeStream(inputStream)
//            } catch (e: Exception) {
//                Log.d("Error", e.stackTrace.toString())
//            }
//
//            return bitmap
//        }
//
//        override fun onPostExecute(result: Bitmap?) {
//            if (result != null) {
//                bmImage.setImageBitmap(result)
//            }
//        }
//    }
}
