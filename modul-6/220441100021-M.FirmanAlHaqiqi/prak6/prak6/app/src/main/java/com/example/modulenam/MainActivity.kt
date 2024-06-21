package com.example.modulenam

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modulenam.retrofit.APIViewModel
import com.example.modulenam.retrofit.Player
import com.example.modulenam.retrofit.ViewModelFactory
import com.google.android.material.imageview.ShapeableImageView

class MainActivity : AppCompatActivity() {
    private lateinit var apiViewModel: APIViewModel
    private lateinit var adapterEnam: AdapterEnam
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.WHITE

        val factory = ViewModelFactory.getInstance()
        apiViewModel = ViewModelProvider(this,
            factory)[APIViewModel::class.java]

        recyclerView = findViewById(R.id.recycler_konten)
        recyclerView.layoutManager = LinearLayoutManager(this)

        apiViewModel.getAllPlayer()
        apiViewModel.listPlayer.observe(this) { players ->
            if (players.isNotEmpty()) {
                adapterEnam = AdapterEnam(players)
                recyclerView.adapter = adapterEnam
                adapterEnam.setOnItemClickCallback(object :
                    AdapterEnam.OnItemClickCallback {
                    override fun onItemClicked(data: Player) {
                        showSelectedPlayer(data)
                    }
                })
            }
        }

        onClick()
    }

    private fun onClick(){
        val profil = findViewById<ShapeableImageView>(R.id.profil)
        profil.setOnClickListener{
            val intent = Intent(this, AuthorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showSelectedPlayer(data: Player) {
        val navigateToDetail = Intent(this, DetailActivity::class.java)
        navigateToDetail.putExtra("players", data)
        startActivity(navigateToDetail)
    }
}