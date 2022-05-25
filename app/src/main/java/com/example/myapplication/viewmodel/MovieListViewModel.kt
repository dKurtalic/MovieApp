package com.example.myapplication.viewmodel

import com.example.myapplication.data.GetMoviesResponse
import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieListViewModel () {

    val scope = CoroutineScope(Job() + Dispatchers.Main)
    fun getFavoriteMovies():List<Movie>{
        return MovieRepository.getFavoriteMovies()
    }
    fun getRecentMovies():List<Movie> {
        return MovieRepository.getRecentMovies()
    }
    fun search(query: String, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.searchRequest(query)
            when (result) {
                is com.example.myapplication.data.Result.Success<List<Movie>>->onSuccess?.invoke(result.data)
                else -> onError?.invoke()
            }
        }
    }

    fun getUpcoming( onSuccess: (movies: List<Movie>) -> Unit,
                     onError: () -> Unit){
        // Create a new coroutine on the UI thread
        scope.launch{
            // Make the network call and suspend execution until it finishes
            val result = MovieRepository.getUpcomingMovies()

            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> onSuccess?.invoke(result.movies)
                else-> onError?.invoke()
            }
        }
    }

}