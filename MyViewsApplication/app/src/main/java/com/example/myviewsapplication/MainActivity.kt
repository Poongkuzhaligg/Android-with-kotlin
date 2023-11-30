package com.example.myviewsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val et : EditText = findViewById(R.id.editText1)
//
////        button
//        val btn: Button = findViewById(R.id.button)
//        btn.setOnClickListener(){
//            val editTextInput: String = et.text.toString()
//
//            Toast.makeText(this@MainActivity,
//                ""+editTextInput,
//                Toast.LENGTH_SHORT).show()
//        }

        val img: ImageView = findViewById(R.id.img)
        img.setImageResource(R.drawable.octlogo)
    }
}