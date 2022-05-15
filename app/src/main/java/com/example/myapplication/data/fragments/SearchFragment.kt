package com.example.myapplication.data.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.util.Pair
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.GravityCompat.apply
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MovieDetailActivity
import com.example.myapplication.R
import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.view.MovieListAdapter
import com.example.myapplication.viewmodel.MovieListViewModel

class SearchFragment : Fragment() {
    private lateinit var searchText: EditText
    private lateinit var prikazSearcha: RecyclerView
    private lateinit var movieListViewModel:MovieListViewModel
    private lateinit var searchMoviesAdapter:MovieListAdapter
    private lateinit var searchButton: ImageButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.search_fragment, container, false)
        prikazSearcha=view.findViewById(R.id.prikazSearcha)
        searchText = view.findViewById(R.id.searchText)
        arguments?.getString("search")?.let {
            searchText.setText(it)
        }
        searchButton=view.findViewById(R.id.searchButton)
        searchMoviesAdapter= MovieListAdapter(arrayListOf()){
                movie, view1, view2 -> showMovieDetails(movie,view1,view2) }
        movieListViewModel = MovieListViewModel(this@SearchFragment::searchDone,
                                                this@SearchFragment::onError)
        prikazSearcha.adapter=searchMoviesAdapter
        prikazSearcha.layoutManager=GridLayoutManager(activity,2)
        searchButton.setOnClickListener { onClick() }
        return view;
    }
    companion object {
        fun newInstance(search:String): SearchFragment = SearchFragment().apply {
            arguments = Bundle().apply {
                putString("search", search)
            }
        }
    }
    fun searchDone(movies:List<Movie>){
        val toast = Toast.makeText(context, "Search done", Toast.LENGTH_SHORT)
        toast.show()
        searchMoviesAdapter.updateMovies(movies)
    }
    private fun onClick() {
        val toast = Toast.makeText(context, "Search start", Toast.LENGTH_SHORT)
        toast.show()
        movieListViewModel.search(searchText.text.toString())
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
    private fun showMovieDetails(movie: Movie,view1:View,view2:View) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_id", movie.id)
        }
        val options = ActivityOptions
            .makeSceneTransitionAnimation(activity,  Pair.create(view1, "poster"),
                Pair.create(view2, "title"))
        startActivity(intent, options.toBundle())
    }
}