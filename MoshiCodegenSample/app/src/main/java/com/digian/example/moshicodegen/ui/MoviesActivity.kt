package com.digian.example.moshicodegen.ui

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digian.example.moshicodegen.R
import com.digian.example.moshicodegen.data.PopularMovies
import com.digian.example.moshicodegen.data.PopularMoviesRepository
import com.digian.example.moshicodegen.data.PopularMoviesRepositoryImpl

class MoviesActivity : Activity(), PopularMoviesRepository {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        viewManager = LinearLayoutManager(this)


        viewAdapter = MoviesAdapter(listOf("Hello 1", "Hello 2"))

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    override fun getMovies() : PopularMovies {
        return PopularMoviesRepositoryImpl(this).getMovies()
    }
}
