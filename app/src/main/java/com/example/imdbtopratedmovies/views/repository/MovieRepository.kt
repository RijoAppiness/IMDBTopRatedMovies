package com.example.imdbtopratedmovies.views.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.imdbtopratedmovies.R
import com.example.imdbtopratedmovies.views.models.Data
import com.example.imdbtopratedmovies.views.models.Movie
import com.example.imdbtopratedmovies.views.network.API_KEY
import com.example.imdbtopratedmovies.views.network.MovieAPI
import com.example.imdbtopratedmovies.views.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class is the single source of truth for movies
 */

class MovieRepository(val context:Application){
  private val movieAPI = RetrofitClient.getInstance().create(MovieAPI::class.java)
    val errorMessage = MutableLiveData<String>()
    val topRatedMovies = MutableLiveData<List<Movie>>()
  fun requestTopRatedMovies(){
      val call = movieAPI.getTopRatedMovies(API_KEY)
      call.enqueue(object :Callback<Data>{
          override fun onFailure(call: Call<Data>, t: Throwable) {
              errorMessage.postValue(context.getString(R.string.could_not_reach_server))
              t.printStackTrace()
          }

          override fun onResponse(call: Call<Data>, response: Response<Data>) {
              if(response.isSuccessful){
                  val movieList = response.body()?.results
                  topRatedMovies.postValue(movieList)
              }
              else{
                  errorMessage.postValue(context.getString(R.string.server_response_error))
              }
          }

      })
  }
}