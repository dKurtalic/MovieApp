package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes

class MyArrayAdapter (context: Context, @LayoutRes private val layoutResource: Int, private val elements: ArrayList<String>):
ArrayAdapter<String>(context, layoutResource, elements){

    override fun getView(position: Int, newView: View?, parent: ViewGroup): View {
        var newView=newView
        newView=LayoutInflater.from(context).inflate(R.layout.element_liste, parent,false)
        val textView=newView.findViewById<TextView>(R.id.textElement)
        val element=elements.get(position)
        textView.text=element
        return newView
    }

}