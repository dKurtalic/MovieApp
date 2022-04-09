package com.example.myapplication.data.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MovieDetailActivity
import com.example.myapplication.R
import com.example.myapplication.data.Movie
import com.example.myapplication.view.MovieListAdapter
import com.example.myapplication.viewmodel.MovieListViewModel

class RecentMoviesFragment: Fragment() {
    private lateinit var recentMovies: RecyclerView
    private lateinit var recentMoviesAdapter: MovieListAdapter
    private var MovieListViewModel= MovieListViewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view= inflater.inflate(R.layout.recent_fragment,container,false)
        recentMovies=view.findViewById(R.id.recentMovies)
        recentMoviesAdapter= MovieListAdapter(arrayListOf()) {
            movie -> showMovieDetails(movie)
        }
        recentMovies.layoutManager=GridLayoutManager(activity,2)
        recentMovies.adapter=recentMoviesAdapter
        recentMoviesAdapter.updateMovies(MovieListViewModel.getRecentMovies())
        return view
    }

    private fun showMovieDetails(movie: Movie) {
        val intent= Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_title",movie.title)
        }
        startActivity(intent)
    }
    companion object {
        fun newInstance():RecentMoviesFragment= RecentMoviesFragment()
    }
}