package com.example.myapplication.viewmodel

import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository
class MovieDetailViewModel {
    fun getMovieByTitle(name: String):Movie{
        var movies : ArrayList<Movie> = arrayListOf()
        movies.addAll(MovieRepository.getRecentMovies())
        movies.addAll(MovieRepository.getFavoriteMovies())
        val movie = movies.find {movie-> name.equals(movie.title)}
        return movie?:Movie(0,"Test","Test","Test","Test","Test")
    }
    fun getActorsByTitle(name:String):List<String>{
        return MovieRepository.getActors()[name] ?: emptyList()
    }
    fun getSimilarByTitle(name:String):List<String>{
        return MovieRepository.getSimilarMovies()?.get(name)?: emptyList()
    }
}