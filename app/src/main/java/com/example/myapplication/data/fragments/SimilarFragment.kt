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

class SimilarFragment(name:String): Fragment() {
    var str=name
    private var movieDetailActivity= MovieDetailViewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.similar_fragment,container,false)
        var similar=view.findViewById<RecyclerView>(R.id.listSimilar)
        similar.layoutManager=LinearLayoutManager(activity)
        var lista=movieDetailActivity.getSimilarByTitle(str)
        var adapter=SimpleStringAdapter(lista)
        similar.adapter=adapter
        return view
    }
}