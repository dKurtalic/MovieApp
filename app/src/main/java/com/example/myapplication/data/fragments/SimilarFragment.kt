package com.example.myapplication.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MovieDetailActivity
import com.example.myapplication.R
import com.example.myapplication.view.SimpleStringAdapter
import com.example.myapplication.viewmodel.MovieDetailViewModel

class SimilarFragment(name:String,id:Long?): Fragment() {
    var str=name
    var id=id
    var lista= listOf<String>()
    lateinit var adapter:SimpleStringAdapter
    private var movieDetailActivity= MovieDetailViewModel(null,null,this@SimilarFragment::similarMoviesFound,null)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.similar_fragment,container,false)
        var similar=view.findViewById<RecyclerView>(R.id.listSimilar)
        similar.layoutManager=LinearLayoutManager(activity)
        lista=movieDetailActivity.getSimilarByTitle(str)!!
        adapter=SimpleStringAdapter(lista)
        id?.let { movieDetailActivity.getSimilarById(it) }

        similar.adapter=adapter
        return view
    }
    fun similarMoviesFound(similarmovies:List<String>){
        this.lista=similarmovies
        adapter.list=similarmovies
        adapter.notifyDataSetChanged()
    }
}