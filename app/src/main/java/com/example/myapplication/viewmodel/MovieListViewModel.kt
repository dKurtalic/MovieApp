package com.example.myapplication.viewmodel

import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieListViewModel (private val searchDone: ((movies: List<Movie>) -> Unit)?,
                          private val onError: (()->Unit)?) {

    val scope = CoroutineScope(Job() + Dispatchers.Main)
    fun getFavoriteMovies():List<Movie>{
        return MovieRepository.getFavoriteMovies()
    }
    fun getRecentMovies():List<Movie> {
        return MovieRepository.getRecentMovies()
    }
    fun search(query: String){
        scope.launch{
            val result = MovieRepository.searchRequest(query)
            when (result) {
                is com.example.myapplication.data.Result.Success<List<Movie>>->searchDone?.invoke(result.data)
                else -> onError?.invoke()
            }
        }
    }
}