package com.example.myapplication.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MovieDetailActivity
import com.example.myapplication.R
import com.example.myapplication.view.SimpleStringAdapter
import com.example.myapplication.viewmodel.MovieDetailViewModel

class ActorsFragment(movieName:String,id:Long?): Fragment() {
    var str:String=movieName
    var lista=listOf<String>()
    var id=id
    lateinit var actorsRVSimpleAdapter:SimpleStringAdapter
    private var movieDetailViewModel=MovieDetailViewModel(null,null,null,this@ActorsFragment::actorsFound)
    lateinit var  listSimilar: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view=inflater.inflate(R.layout.actors_fragment,container,false)
        listSimilar=view.findViewById(R.id.listSimilar)
        listSimilar.layoutManager=LinearLayoutManager(activity)
        lista=movieDetailViewModel.getActorsByTitle(str)
        actorsRVSimpleAdapter = SimpleStringAdapter(lista)
        listSimilar.adapter = actorsRVSimpleAdapter
        id?.let { movieDetailViewModel.getActorsById(it) }
        return view
    }
    fun actorsFound(actors:List<String>){
        this.lista=actors
        actorsRVSimpleAdapter.list=actors
        actorsRVSimpleAdapter.notifyDataSetChanged()
    }
}