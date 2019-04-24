package com.digian.example.moshicodegen.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.digian.example.moshicodegen.R
import com.digian.example.moshicodegen.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import android.net.Uri
import android.util.Log
import com.squareup.picasso.Callback
import java.lang.Exception

const val UNKNOWN_MOVIE_ID = 0
const val IMAGE_URL_AND_PATH = "https://image.tmdb.org/t/p/w500"

class MovieDetailFragment : Fragment() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        val movieId: Int = arguments?.getInt("movieId") ?: UNKNOWN_MOVIE_ID

        movieDetailViewModel.getMovie(movieId).observe(this,
            Observer<Movie> { movie ->

                movie?.let {
                    movie_title.text = movie.title
                    movie_description.text = movie.overview
                    loadImageView(movie.posterPath)
                }
            })
    }

    private fun loadImageView(posterPath: String?) {

        val uri : Uri = Uri.parse(IMAGE_URL_AND_PATH.plus(posterPath))

        val picasso = Picasso.get()
        picasso.isLoggingEnabled = true

        picasso
            .load(uri)
            .error(R.drawable.baseline_error_black_48dp)
            .into(movie_image, object : Callback {
                override fun onSuccess() {
                    Log.d("PICASSO RESULT", "onSuccess")
                }

                override fun onError(e: Exception?) {
                    Log.e("PICASSO RESULT", "onError", e)
                }
            })
    }

}
