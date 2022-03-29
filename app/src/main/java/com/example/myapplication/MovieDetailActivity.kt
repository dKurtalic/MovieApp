package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.Movie
import com.example.myapplication.viewmodel.MovieDetailViewModel


class MovieDetailActivity : AppCompatActivity(){
    private var movieDetailViewModel =  MovieDetailViewModel()
    private lateinit var movie: Movie
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details_layout)
        title=findViewById(R.id.movie_title)
        releaseDate=findViewById(R.id.movie_release_date)
        genre=findViewById(R.id.movie_genre)
        overview=findViewById(R.id.movie_overview)
        website=findViewById(R.id.movie_website)
        poster=findViewById(R.id.movie_poster)

        val extras=intent.extras
        if (extras!=null){
            movie=movieDetailViewModel.getMovieByTitle(extras.getString("movie_title",""))
            populateDetails()
        }
        else {
            finish()
        }
        website.setOnClickListener(){showWebsite()}

    }
    private fun showWebsite(){
        val webIntent : Intent = Uri.parse(movie.homepage).let{ webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        try {
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
        }
    }

    private fun populateDetails(){
        title.text=movie.title
        genre.text=movie.genre
        overview.text=movie.overview
        releaseDate.text=movie.releaseDate
        website.text=movie.homepage
        val context: Context=poster.context
        var id: Int=context.resources.getIdentifier(movie.genre, "drawable",context.packageName)
        if (id==0){
            id=context.resources.getIdentifier("romantic","drawable",context.packageName)
        }
        poster.setImageResource(id)

    }

}