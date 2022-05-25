package com.example.myapplication.viewmodel

import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieIsTaken: ((movie:Movie)->Unit)?,
                            private val movieNotTaken: (()->Unit)?,
                            private val similarMoviesFound:((movies:List<String>)->Unit)?,
                            private val actorsFound: ((actors:List<String>)->Unit)? ) {
    var scope=  CoroutineScope(Job() + Dispatchers.Main)

    fun getMovieByTitle(name: String):Movie{
        var movies : ArrayList<Movie> = arrayListOf()
        movies.addAll(MovieRepository.getRecentMovies())
        movies.addAll(MovieRepository.getFavoriteMovies())
        val movie = movies.find {movie-> name.equals(movie.title)}
        return movie?:Movie(0,"Test","Test","Test","Test",null,null)


    }
    fun getActorsByTitle(name:String):List<String>{
        return MovieRepository.getActors()[name] ?: emptyList()
    }
    fun getSimilarByTitle(name:String):List<String>{
        return MovieRepository.getSimilarMovies()?.get(name)?: emptyList()
    }
    fun getSimilarById(id:Long) {
        scope.launch{
            val result=MovieRepository.searchSimilarMovies(id)
            when (result){
                is Result.Success<List<String>> -> similarMoviesFound?.let { it(result.data) }
                else -> Error()
            }
        }
    }
    fun getActorsById(id:Long){
        scope.launch {
            val result=MovieRepository.searchActors(id)
            when(result){
                is Result.Success<List<String>> -> actorsFound?.let{ it(result.data)}
                else -> Error()
                }

        }
    }
    fun searchSpecificMovie(query: Long) {
        scope.launch {
            val result=MovieRepository.searchMovie(query)
            when(result){
                is Result.Success<Movie> -> movieIsTaken?.invoke(result.data)
                else -> movieNotTaken?.invoke()
            }
        }

    }
}