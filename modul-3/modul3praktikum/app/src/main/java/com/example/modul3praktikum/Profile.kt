package com.example.modul3praktikum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Profile: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aactivity_profile)
        val getDataName = intent.getStringExtra("name")
        val back  = findViewById<Button>(R.id.backtomainactivity)
        back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("name", getDataName.toString())
            startActivity(intent)
        }
    }
}
