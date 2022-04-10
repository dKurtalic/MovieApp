package com.example.myapplication.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.MovieDetailActivity
import com.example.myapplication.R

class ActorsFragment: Fragment() {
    lateinit var frameLayoutActors:FrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view=inflater.inflate(R.layout.actors_fragment,container,false)
        var actors=MovieDetailActivity
    }
}