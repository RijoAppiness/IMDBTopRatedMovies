package com.example.imdbtopratedmovies.views.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class is a singleton to get retrofit client
 */

class RetrofitClient{
    companion object {
        private var retroInstance:Retrofit? = null
        fun getInstance(): Retrofit {
            if(retroInstance==null){
                retroInstance = createRetrofit()
            }
            return retroInstance as Retrofit
        }
        fun createRetrofit():Retrofit{
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).
                    build()
            return retrofit
        }
    }

}