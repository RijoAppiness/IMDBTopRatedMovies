package com.example.imdbtopratedmovies.views.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.imdbtopratedmovies.R
import com.example.imdbtopratedmovies.views.home_screen.HomeSreen

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(object:Runnable{
            override fun run() {
                startActivity(Intent(this@SplashActivity, HomeSreen::class.java))
                finish()
            }

        },2000)
    }
}
