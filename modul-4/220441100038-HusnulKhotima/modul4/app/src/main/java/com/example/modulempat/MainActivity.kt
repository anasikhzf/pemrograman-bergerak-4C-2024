package com.example.modulempat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.modulempat.adapter.kontenAdapter
import com.example.modulempat.room.kontenViewModel
import com.example.modulempat.room.kontenViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var appViewModel: kontenViewModel

    private lateinit var kontenAdapter: kontenAdapter

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = kontenViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[kontenViewModel::class.java]

        recyclerView = findViewById(R.id.recycler_konten)
//        recyclerView.layoutManager = StaggeredGridLayoutManager(this)

        appViewModel.getAllKonten().observe(this) {kontenData ->
            if (kontenData != null){
                kontenAdapter = kontenAdapter(kontenData)
                recyclerView.adapter = kontenAdapter

            }
        }

        val btnAdd = findViewById<FloatingActionButton>(R.id.addButton)
        btnAdd.setOnClickListener{
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }


    }



    override fun onRestart() {
        super.onRestart()

        // Memperbarui daftar pemain
        appViewModel.getAllKonten()
    }
}