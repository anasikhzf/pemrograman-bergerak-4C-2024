package com.example.prak2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button = findViewById<ImageView>(R.id.imageView3)
        button.setOnClickListener {
            val lanjut = Intent(this, MainActivity3::class.java)
            startActivity(lanjut)
        }
    }
}