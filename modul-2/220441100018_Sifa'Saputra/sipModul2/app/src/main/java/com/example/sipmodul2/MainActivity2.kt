package com.example.sipmodul2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.button.MaterialButton

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val profil = findViewById<ImageView>(R.id.logo1)
        profil.setOnClickListener{
            val intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }
    }
}