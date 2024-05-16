package com.example.modul3praktikum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3praktikum.adapter.HorizontalPlaceAdapter
import com.example.modul3praktikum.data.DataPlaceList
import com.example.modul3praktikum.R
import com.example.modul3praktikum.adapter.DetailActivity
import com.example.modul3praktikum.adapter.GridHotelAdapter
import com.example.modul3praktikum.data.DataHotel
import com.example.modul3praktikum.data.DataPlace
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var horizontalPlaceAdapter: HorizontalPlaceAdapter
    private lateinit var recyclerviewHorizontal: RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridHotelAdapter: GridHotelAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Mengambil data nama dari intent
        val getDataName = intent.getStringExtra("name")

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<MaterialTextView>(R.id.greeting_text)
        displayTitle.text = "Halo, $getDataName"

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rv_hotels)
        recyclerviewHorizontal = findViewById(R.id.rv_place_horizontal)

        // Set up RecyclerView
        setupRecyclerView()
        val seemoreplace: TextView = findViewById(R.id.see_all_hot_places)
        seemoreplace.setOnClickListener {
            val intent = Intent(this, LinearActivity::class.java)
            intent.putExtra("name", getDataName)
            startActivity(intent)
        }
        val seemorehotel: TextView = findViewById(R.id.see_all_hotels)
        seemorehotel.setOnClickListener {
            val intent = Intent(this, LinearHotelActivity::class.java)
            intent.putExtra("name", getDataName)
            startActivity(intent)
        }
        val seeprofile: ImageButton = findViewById(R.id.profile)
        seeprofile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("name", getDataName)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        // Initialize adapter with dummy data
        horizontalPlaceAdapter = HorizontalPlaceAdapter(DataPlaceList.horizonDataPlaceDummy)
        gridHotelAdapter = GridHotelAdapter(DataPlaceList.gridDataHotelDummy)
        // ketika item di klik
        // pada bagian setupRecyclerView()
        // ketika item di klik
        horizontalPlaceAdapter.setOnItemClickCallback(object : HorizontalPlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataPlace) {
                showSelectedPlace(data)
            }
        })
        gridHotelAdapter.setOnItemClickCallback(object : GridHotelAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataHotel) {
                showSelectedHotel(data)
            }
        })
        // Set layout manager and adapter for RecyclerView
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = horizontalPlaceAdapter
        recyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = gridHotelAdapter
    }

    private fun showSelectedPlace(data: DataPlace) {
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
    private fun showSelectedHotel(data: DataHotel) {
        // Membuat intent untuk berpindah ke DetailActivity
        val navigateToDetail = Intent(this, DetailActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailActivity
        navigateToDetail.putExtra("name", data.nama)
        navigateToDetail.putExtra("desc", data.desc)
        navigateToDetail.putExtra("image", data.image)



        // Memulai activity baru
        startActivity(navigateToDetail)


    }


}
