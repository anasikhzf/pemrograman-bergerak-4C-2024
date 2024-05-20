package com.example.modul2pember



import android.content.Intent
import android.widget.ImageView
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.example.modul2pember.R.id.button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<MaterialButton>(button)
        button.setOnClickListener {
            val lanjut = Intent(this, t2 ::class.java)
            startActivity(lanjut)
        }
    }
}