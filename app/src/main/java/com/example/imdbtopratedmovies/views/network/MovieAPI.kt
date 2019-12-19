package com.example.imdbtopratedmovies.views.network

import com.example.imdbtopratedmovies.views.models.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI{
  @GET("top_rated")
  fun getTopRatedMovies(@Query("api_key") api_key:String): Call<Data>
}