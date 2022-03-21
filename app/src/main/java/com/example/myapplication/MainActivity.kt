package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private lateinit var button: Button
    private val listaVrijednosti = arrayListOf<String>()
    private lateinit var adapter: MyArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button=findViewById<Button>(R.id.button1)
        editText=findViewById<EditText>(R.id.editText1)
        listView=findViewById<ListView>(R.id.listView1)
        adapter = MyArrayAdapter(this,R.layout.element_liste, listaVrijednosti)
        listView.adapter=adapter

        button.setOnClickListener(){
          addToList()
        }

    }
    private fun addToList() {
        listaVrijednosti.add(0,editText.text.toString());
        adapter.notifyDataSetChanged();
        editText.setText("");
    }


}