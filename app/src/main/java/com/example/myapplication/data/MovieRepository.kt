package com.example.myapplication.data

object MovieRepository {
   fun getFavoriteMovies():List<Movie>{
        return favoriteMovies()
    }
    fun getRecentMovies():List<Movie>{
        return recentMovies()
    }
    fun getActors():Map<String,List<String>>{
        return movieActors()
    }
    fun getSimilarMovies():Map<String,List<String>>{
        return similarMovies()
    }
}