package com.vincent.mymovie.view.movies

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vincent.entities.Movie
import com.vincent.mymovie.R


class MovieListAdapter(var movieList: List<Movie>, var movieListFragment: MovieListFragment) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.card_movie, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")

        val movie = movieList[position]
        val cardView = holder.cardView
        val posterImageView = cardView.findViewById(R.id.posterImageView) as ImageView
        val titleTextView = cardView.findViewById(R.id.titleTextView) as TextView
        val yearTextView = cardView.findViewById(R.id.yearTextView) as TextView

        if (!movie.imageUrl.contentEquals("N/A")) {
            DownloadImage(posterImageView).execute(movie.imageUrl)
        }
        titleTextView.text = movie.title
        yearTextView.text = movie.year
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    inner class DownloadImage(bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {
        internal var bmImage: ImageView

        init {
            this.bmImage = bmImage
        }

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay = urls[0]

            Log.d(TAG, "urldisplay=$urldisplay")

            var bitmap: Bitmap? = null
            try {
                val inputStream = java.net.URL(urldisplay).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.d("Error", e.stackTrace.toString())
            }

            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                bmImage.setImageBitmap(result)
            }
        }
    }
}
