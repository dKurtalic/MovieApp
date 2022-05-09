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
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Movie

class MovieListAdapter
    (private var moviesArray: List<Movie>,
     private val onItemClicked: (movie:Movie,view1:View,view2:View) -> Unit)
    : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val posterPath = "https://image.tmdb.org/t/p/w342"

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
        val genreMatch: String? = moviesArray[position].genre
        val context: Context = holder.movieImage.context

        var id= 0;
        if (genreMatch!==null)
            id = context.resources
                .getIdentifier(genreMatch, "drawable", context.packageName)
        if (id===0) id=context.resources
            .getIdentifier("picture1", "drawable", context.packageName)
        Glide.with(context)
            .load(posterPath + moviesArray[position].posterPath)
            .centerCrop()
            .placeholder(R.drawable.romantic)
            .error(id)
            .fallback(id)
            .into(holder.movieImage);

        holder.movieImage.setImageResource(id)

        holder.itemView.setOnClickListener{
            onItemClicked(moviesArray[position],holder.movieImage,holder.movieTitle)
        }
        }

    override fun getItemCount(): Int {
        return moviesArray.size
    }

    fun updateMovies(movies: List<Movie>) {
        this.moviesArray = movies
        notifyDataSetChanged()
    }
}

