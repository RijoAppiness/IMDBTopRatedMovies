package com.example.imdbtopratedmovies.views.models

import com.google.gson.annotations.SerializedName

/**
 * This class is a model class to hold the list of movies
 */

data class Data(@SerializedName("results") val results:List<Movie>)