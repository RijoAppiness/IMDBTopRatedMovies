package com.example.imdbtopratedmovies.views.models

import com.google.gson.annotations.SerializedName

/**
 * This class is a model class to hold movies
 */

data class Movie(@SerializedName("id") val imdb_id:String,@SerializedName("title") val title:String,
                 @SerializedName("release_date") val release_date:String,
                 @SerializedName("vote_average") val rating:Double,
                 @SerializedName("poster_path") val poster:String)