package com.example.imdbtopratedmovies.views.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.imdbtopratedmovies.R
import com.example.imdbtopratedmovies.views.home_screen.IMDB_ID
import kotlinx.android.synthetic.main.activity_web_view.*

//const val IMDB_BASE_URL = "https://www.imdb.com/title/"
const val IMDB_BASE_URL = "https://www.themoviedb.org/movie/"
class WebViewActivity : AppCompatActivity() {
    private lateinit var imdb_id:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        imdb_id = intent.getStringExtra(IMDB_ID)
        WB_imdb.webViewClient = IMDB()
        WB_imdb.settings.javaScriptEnabled = true
        WB_imdb.loadUrl(IMDB_BASE_URL+imdb_id)
    }
    class IMDB: WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
    }
}
