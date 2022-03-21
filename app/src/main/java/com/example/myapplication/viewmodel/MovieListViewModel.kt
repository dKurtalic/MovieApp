package com.example.myapplication.viewmodel

import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository

class MovieListViewModel {
    fun getFavoriteMovies():List<Movie>{
        return MovieRepository.getFavoriteMovies()
    }
    fun getRecentMovies():List<Movie> {
        return MovieRepository.getRecentMovies()
    }
}