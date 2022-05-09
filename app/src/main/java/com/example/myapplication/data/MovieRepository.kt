package com.example.myapplication.data


import com.example.myapplication.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

object MovieRepository {
    private val tmdb_api_key : String = BuildConfig.TMDB_API_KEY
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


    suspend fun searchRequest(
        query: String
    ): Result<List<Movie>> {
        return withContext(Dispatchers.IO) {
            try {
                val movies = arrayListOf<Movie>()
                val url1 = "https://api.themoviedb.org/3/search/movie?api_key=$tmdb_api_key&query=$query" //1
                val url = URL(url1) //2
                (url.openConnection() as? HttpURLConnection)?.run { //3
                    val result = this.inputStream.bufferedReader().use { it.readText() } //4
                    val jo = JSONObject(result)//5
                    val results = jo.getJSONArray("results")//6
                    for (i in 0 until results.length()) {//7
                        val movie = results.getJSONObject(i)
                        val title = movie.getString("title")
                        val id = movie.getInt("id")
                        val posterPath = movie.getString("poster_path")
                        val overview = movie.getString("overview")
                        val releaseDate = movie.getString("release_date")
                        movies.add(Movie(id.toLong(), title, overview, releaseDate, null, null, posterPath))
                        if (i == 5) break
                    }
                }
                return@withContext Result.Success(movies);//8
            }
            catch (e: MalformedURLException) {
                return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
            } catch (e: IOException) {
                return@withContext Result.Error(Exception("Cannot read stream"))
            } catch (e: JSONException) {
                return@withContext Result.Error(Exception("Cannot parse JSON"))
            }
        }
    }
}

