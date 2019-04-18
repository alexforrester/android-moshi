package com.digian.example.moshicodegen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Alex Forrester on 17/04/2019.
 */
class MoviesAdapter(private val movieDataSet: List<String>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.test_list_item, parent, false) as TextView

        return MovieViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = movieDataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = movieDataSet.size
}