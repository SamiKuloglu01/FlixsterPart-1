package com.nexustech.flixsterpart1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textViewTitle)
        val overview: TextView = view.findViewById(R.id.textViewOverview)
        val poster: ImageView = view.findViewById(R.id.imageViewPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.overview.text = movie.overview

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.poster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
