package com.vincent.mymovie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResult {

    @SerializedName("Search")
    @Expose
    var search: List<MovieModel>? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null
    @SerializedName("Response")
    @Expose
    var response: String = "False"

}