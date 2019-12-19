package com.example.imdbtopratedmovies.views.views.home_screen

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.imdbtopratedmovies.views.repository.MovieRepository

class HomeViewModel(context: Application): AndroidViewModel(context){
    private val repository = MovieRepository(context)
    val observableMovieResponse = repository.topRatedMovies
    val observableErrorMessage = repository.errorMessage
    fun loadData() {
        repository.requestTopRatedMovies()
    }


}