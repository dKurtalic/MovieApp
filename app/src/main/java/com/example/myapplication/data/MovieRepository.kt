package com.example.myapplication.data


import android.util.JsonReader
import android.util.Log
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

     suspend fun searchMovie(id:Long):Result<Movie>{
        var film=Movie(0,"Test","Test","Test","Test","Test",null,null)
        return withContext(Dispatchers.IO){
            try {
                val url1="https://api.themoviedb.org/3/movie/$id?api_key=$tmdb_api_key"
                val url=URL(url1)
                (url.openConnection() as? HttpURLConnection)?.run { //3
                    val result = this.inputStream.bufferedReader().use { it.readText() }
                    val jo = JSONObject(result)
                    val title = jo.getString("title")
                    val id = jo.getLong("id")
                    val posterPath = jo.getString("poster_path")
                    val overview = jo.getString("overview")
                    val releaseDate = jo.getString("release_date")
                    val zanrovi= jo.getJSONArray("genres")
                    val genre=zanrovi.getJSONObject(0).getString("name")
                    val homepage=jo.getString("homepage")
                    val backdropPath=jo.getString("backdrop_path")
                    Log.v("Movie repos",id.toString())
                    film= Movie(id, title, overview, releaseDate,homepage ,genre,posterPath,backdropPath)
                     }
                return@withContext Result.Success(film)
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
    suspend fun searchSimilarMovies(id:Long):Result<List<String>>{
        val similarMovies=arrayListOf<String>()
        return withContext(Dispatchers.IO){
            try{
                val url1="https://api.themoviedb.org/3/movie/$id/similar?api_key=$tmdb_api_key"
                val url=URL(url1)
                (url.openConnection() as HttpURLConnection)?.run{
                    val result=this.inputStream.bufferedReader().use { it.readText() }
                    val jo=JSONObject(result)
                    val results=jo.getJSONArray("results")
                    for (i in 0 until results.length()){
                        val movie=results.getJSONObject(i)
                        val title = movie.getString("title")
                        similarMovies.add(title)
                        if (i == 5) break
                    }

                }
                return@withContext Result.Success(similarMovies)
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
    suspend fun searchActors(id:Long):Result<List<String>>{
        return withContext(Dispatchers.IO){
            try {
                val actors= arrayListOf<String>()
                val url1="https://api.themoviedb.org/3/movie/$id/credits?api_key=$tmdb_api_key"
                val url=URL(url1)
                (url.openConnection() as HttpURLConnection).run {
                    val result=this.inputStream.bufferedReader().use { it.readText() }
                    val jo=JSONObject(result)
                    val results=jo.getJSONArray("cast")
                    for (i in 0 until results.length()){
                        actors.add(results.getJSONObject(i).getString("name"))
                        if (i==5) break
                    }
                }
                return@withContext Result.Success(actors)
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
    suspend fun searchRequest(query: String): Result<List<Movie>> {
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
                        movies.add(Movie(id.toLong(), title, overview, releaseDate, null, null, posterPath,null))
                        if (i == 5) break
                    }
                }
                return@withContext Result.Success(movies)
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

