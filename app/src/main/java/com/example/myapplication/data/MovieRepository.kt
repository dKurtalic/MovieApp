package com.example.myapplication.data

object MovieRepository {
    fun getFavoriteMovies():List<Movie>{
        return favoriteMovies()
    }
    fun getRecentMovies():List<Movie>{
        return recentMovies()
    }
}