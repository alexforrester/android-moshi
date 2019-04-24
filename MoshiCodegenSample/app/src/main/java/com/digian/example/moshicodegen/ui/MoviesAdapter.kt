package com.digian.example.moshicodegen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digian.example.moshicodegen.data.Movie


/**
 * Created by Alex Forrester on 17/04/2019.
 */
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    internal var data: List<Movie>? = null

    class MovieViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(com.digian.example.moshicodegen.R.layout.movies_list_item, parent, false) as TextView

        return MovieViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.textView.text = data?.get(position)?.title
    }

    override fun getItemCount() = data?.size ?: 0
}