package com.example.warover

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warover.adapter.playerAdapter
import com.example.warover.remote.PlayerData
import com.example.warover.remote.playerViewModel
import com.example.warover.remote.playerViewModelFactory
import com.google.android.material.imageview.ShapeableImageView

class MainActivity : AppCompatActivity() {
    private lateinit var PlayerViewModel: playerViewModel
    private lateinit var adapter: playerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageProfile = findViewById<ShapeableImageView>(R.id.imageProfile)
        imageProfile.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        val factory = playerViewModelFactory.getInstance()
        PlayerViewModel = ViewModelProvider(this,
            factory)[playerViewModel::class.java]
        recyclerView = findViewById(R.id.rvPlayer)
        recyclerView.layoutManager = LinearLayoutManager(this)
        PlayerViewModel.getAllPlayer()
        PlayerViewModel.listPlayer.observe(this) { players ->
            if (players.isNotEmpty()) {
                adapter = playerAdapter(players)
                recyclerView.adapter = adapter
                adapter.setOnItemClickCallback(object :
                    playerAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: PlayerData) {
                        showSelectedPlayer(data)
                    }
                })
            }
        }

    }

    private fun showSelectedPlayer(data: PlayerData) {
        val navigateToDetail = Intent(this, DetailActivity::class.java)
        navigateToDetail.putExtra("players", data)
        startActivity(navigateToDetail)
    }
}
