package com.example.tugasmodul2praktikum

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Beranda : AppCompatActivity() {

    private lateinit var utmbtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        utmbtn = findViewById(R.id.utmbtn)

        utmbtn.setOnClickListener {
            val bukalanjut = Intent(this@Beranda, utm::class.java)
            startActivity(bukalanjut)
        }
    }
}