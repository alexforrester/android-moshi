package com.digian.example.moshicodegen.ui


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.digian.example.moshicodegen.R
import com.digian.example.moshicodegen.data.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*

const val UNKNOWN_MOVIE_ID = 0
const val IMAGE_URL_AND_PATH = "https://image.tmdb.org/t/p/w400"
const val PICASSO_RESULT = "PICASSO_RESULT"

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

        //Loads movie detail and returns from observer or displays error view
        movieDetailViewModel.getMovie(movieId).observe(this,
            Observer<Movie> { movie ->

                movie?.let {
                    movie_title.text = movie.title
                    movie_description.text = movie.overview
                    loadImageView(movie.posterPath)
                    return@Observer
                }

                addErrorView()

            })
    }

    private fun addErrorView() {

        val errorTextView = TextView(activity)
        errorTextView.text = getString(R.string.movie_detail_loading_error)
        errorTextView.gravity = Gravity.CENTER
        errorTextView.textSize = 20f
        errorTextView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        movie_detail_root.addView(errorTextView)
    }

    private fun loadImageView(posterPath: String?) {

        val uri : Uri = Uri.parse(IMAGE_URL_AND_PATH.plus(posterPath))

        val picasso = Picasso.get()
        picasso.isLoggingEnabled = true

        picasso
            .load(uri)
            .error(R.drawable.ic_error_black_80dp)
            .placeholder(R.drawable.placeholder460_690)
            .into(movie_image, object : Callback {
                override fun onSuccess() {
                    Log.d(PICASSO_RESULT, "onSuccess")
                }

                override fun onError(e: Exception?) {
                    Log.e(PICASSO_RESULT, "onError", e)
                }
            })
    }

}
