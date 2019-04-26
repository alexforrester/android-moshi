package com.digian.example.moshireflection.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digian.example.moshireflection.R

/**
 * Created by Alex Forrester on 18/04/2019.
 */
class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        title = getString(R.string.popular_movies)
    }
}
