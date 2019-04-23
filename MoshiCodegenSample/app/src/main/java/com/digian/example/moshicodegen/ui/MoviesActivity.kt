package com.digian.example.moshicodegen.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digian.example.moshicodegen.R
import com.digian.example.moshicodegen.data.PopularMovies
import com.digian.example.moshicodegen.data.PopularMoviesRepository
import com.digian.example.moshicodegen.data.PopularMoviesRepositoryImpl


class MoviesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        viewManager = LinearLayoutManager(this)

        moviesAdapter = MoviesAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = moviesAdapter

            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }

        val popularMoviesRepository: PopularMoviesRepository = PopularMoviesRepositoryImpl(this)

        popularMoviesRepository.popularMoviesLiveData.observe(this,
            Observer<PopularMovies> { popularMovies ->
                moviesAdapter.data = popularMovies.movies
            })

        popularMoviesRepository.setMoviesData()
    }
}
