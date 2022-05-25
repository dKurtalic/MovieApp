package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.data.Movie
import com.example.myapplication.data.fragments.ActorsFragment
import com.example.myapplication.data.fragments.SimilarFragment
import com.example.myapplication.viewmodel.MovieDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView


class MovieDetailActivity : AppCompatActivity(){
    private var movieDetailViewModel = MovieDetailViewModel(this@MovieDetailActivity::movieTaken,this@MovieDetailActivity::movieNotTaken,null,null)

    private  var movie=Movie(0,"Test","Test","Test","Test",null,null)
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView
    private lateinit var backdrop : ImageView
    private lateinit var shareButton: FloatingActionButton
    private lateinit var frameLayoutActors:FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private val posterPath = "https://image.tmdb.org/t/p/w780"
    private val backdropPath = "https://image.tmdb.org/t/p/w500"


    private val bottomNavigationViewListener= NavigationBarView.OnItemSelectedListener {
            item ->  when(item.itemId){
        R.id.actors_bar -> {
            val actorsFragment = ActorsFragment(movie.title,movie.id)
            openFragment(actorsFragment)
            return@OnItemSelectedListener true
        }
        R.id.similarMovies_bar ->{
            val similarFragment= SimilarFragment(movie.title,movie.id)
            openFragment(similarFragment)
            return@OnItemSelectedListener true
        }
    }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        with(window){
            sharedElementExitTransition = Fade()
            exitTransition= Fade()
        }
        setContentView(R.layout.movie_details_layout)
        title=findViewById(R.id.movie_title)
        releaseDate=findViewById(R.id.movie_release_date)
        genre=findViewById(R.id.movie_genre)
        overview=findViewById(R.id.movie_overview)
        website=findViewById(R.id.movie_website)
        poster=findViewById(R.id.movie_poster)
        shareButton=findViewById(R.id.shareButton)
        backdrop=findViewById(R.id.movie_backdrop)
        frameLayoutActors=findViewById(R.id.frame_layout_actors)
        bottomNavigationView=findViewById(R.id.detailNavigation)
        val extras=intent.extras
        if (extras!=null){
            if (extras.containsKey("movie_title")){
                    movie=movieDetailViewModel.getMovieByTitle(extras.getString("movie_title",""))
                    populateDetails()
                 }
            if (extras.containsKey("movie_id"))
                movieDetailViewModel.searchSpecificMovie(extras.getLong("movie_id"))
            }
        else {
            finish()
        }
        website.setOnClickListener(){showWebsite()}
        title.setOnClickListener(){openYouTube()}
        shareButton.setOnClickListener(){shareButtonAction()}

        bottomNavigationView.setOnItemSelectedListener (bottomNavigationViewListener)
        bottomNavigationView.selectedItemId=R.id.actors_bar

    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout_actors, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun shareButtonAction() {
        val intent : Intent = Intent()
        intent.action=Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, overview.text)
        intent.type="text/plain"
        startActivity(Intent.createChooser(intent,"Share to: "))
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

    private fun openYouTube(){

        val titleArray :List<String> =title.text.split(" ").toList()
        var link : String = "https://www.youtube.com/results?search_query=";
        for (x in titleArray ) {
            link+= "$x+"
        }
        link += "trailer"
        var youtubeIntent: Intent=Uri.parse(link).let { webpage->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        try {
            startActivity(youtubeIntent)
        } catch (e: ActivityNotFoundException) {
        }
    }


    private fun populateDetails(){
        title.text=movie.title
        overview.text=movie.overview
        releaseDate.text=movie.releaseDate
        website.text=movie.homepage
        val context: Context=poster.context

        Glide.with(context)
            .load(posterPath + movie.posterPath)
            .placeholder(R.drawable.romantic)
            .into(poster);
        var backdropContext: Context = backdrop.context
        Glide.with(backdropContext)
            .load(backdropPath + movie.backdropPath)
            .centerCrop()
            .placeholder(R.drawable.backdrop)
            .into(backdrop);

    }

    private fun movieTaken(movie:Movie) {
        this.movie=movie
        populateDetails()
    }
    private fun movieNotTaken(){
        Log.v("MovieDetailActivity","Problem sa prikazom detalja o filmu!")
    }

}