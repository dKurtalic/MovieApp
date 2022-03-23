package com.example.myapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Movie

class MovieListAdapter
    (private var moviesArray: List<Movie>): RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

        inner class MovieViewHolder(pogled:View): RecyclerView.ViewHolder(pogled){
            val movieImage=pogled.findViewById<ImageView>(R.id.movieImage)
            val movieTitle=pogled.findViewById<TextView>(R.id.movieTitle)
        }

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): MovieViewHolder {
            val pogled=LayoutInflater.from(vg.context).inflate(R.layout.movie_icon_layout,vg,false)
        return MovieViewHolder(pogled)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text = moviesArray[position].title;
        val genreMatch: String = moviesArray[position].genre

        val context: Context = holder.movieImage.context
        var id: Int = context.resources
            .getIdentifier(genreMatch, "drawable", context.packageName)
        if (id===0) id=context.resources
            .getIdentifier("romantic", "drawable", context.packageName)
        holder.movieImage.setImageResource(id)
        }

    override fun getItemCount(): Int {
        return moviesArray.size
    }

    fun updateMovies(movies: List<Movie>) {
        this.moviesArray = movies
        notifyDataSetChanged()
    }
}

