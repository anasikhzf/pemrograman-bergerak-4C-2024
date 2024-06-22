package com.example.modul3praktikum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3praktikum.adapter.DetailActivity
import com.example.modul3praktikum.adapter.LinearHotelAdapter
import com.example.modul3praktikum.adapter.LinearPlaceAdapter
import com.example.modul3praktikum.data.DataHotel
import com.example.modul3praktikum.data.DataPlace
import com.example.modul3praktikum.data.DataPlaceList

class LinearHotelActivity:AppCompatActivity() {
    private lateinit var linearHotelAdapter: LinearHotelAdapter
    private lateinit var recyclerviewVertical: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seemore_hotels)
        val getDataName = intent.getStringExtra("name")
        recyclerviewVertical = findViewById(R.id.rv_hotelsVerti)
        setupRecyclerView()
        // kembali main activity
        val back  = findViewById<Button>(R.id.backtomainactivity)
        back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("name", getDataName.toString())
            startActivity(intent)
        }
    }
    private fun setupRecyclerView() {
        // Initialize adapter with dummy data
        linearHotelAdapter = LinearHotelAdapter(DataPlaceList.gridDataHotelDummy)
        linearHotelAdapter.setOnItemClickCallback(object : LinearHotelAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataHotel) {
                showSelectedHotel(data)
            }
        })
        // Set layout manager and adapter for RecyclerView
        recyclerviewVertical.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerviewVertical.adapter = linearHotelAdapter
    }
    private fun showSelectedHotel(data: DataHotel) {
        // Membuat intent untuk berpindah ke DetailActivity
        val navigateToDetail = Intent(this, DetailActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailActivity
        navigateToDetail.putExtra("name", data.nama)
        navigateToDetail.putExtra("loc", data.loc)
        navigateToDetail.putExtra("desc", data.desc)
        navigateToDetail.putExtra("image", data.image)



        // Memulai activity baru
        startActivity(navigateToDetail)
    }
}