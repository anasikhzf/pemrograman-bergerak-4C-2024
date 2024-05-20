package com.example.modul2pember

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class t2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.t2)

        val button = findViewById<ImageView>(R.id.imageView)
        button.setOnClickListener {
            val lanjut = Intent(this, t3::class.java)
            startActivity(lanjut)
        }
    }
}
