package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button=findViewById<Button>(R.id.button1)
        button.setOnClickListener(){
            showMessage()
        }

    }
    private fun showMessage(){
        val editText=findViewById<EditText>(R.id.editText1)
        val textView=findViewById<TextView>(R.id.textView)
        val message=editText.text.toString()
        textView.text=message
    }

}