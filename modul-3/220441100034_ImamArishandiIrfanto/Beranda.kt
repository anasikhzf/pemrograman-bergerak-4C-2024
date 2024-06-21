package com.example.hotels

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.data.PlacesData
import com.exemple.hotels.adapter.HotelAdapterGrid
import com.exemple.hotels.adapter.PlaceAdapter
import com.exemple.hotels.data.DataList
import com.exemple.hotels.data.HotelsData
import com.google.android.material.textview.MaterialTextView

class Beranda : AppCompatActivity() {
    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var hotelAdapterGrid: HotelAdapterGrid
    private lateinit var placeAdapterHorizontal: PlaceAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerviewHorizontal: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        // Mengambil data nama dari intent
        val getDataName = intent.getStringExtra("name")

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<MaterialTextView>(R.id.greeting_text)
        displayTitle.text = "Halo, $getDataName"

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_hotel)
        recyclerviewHorizontal = findViewById(R.id.rv_place_horizontal)

        // Menginisialisasi semua adapter dengan data
        hotelAdapterGrid = HotelAdapterGrid(DataList.HotelDum)
        placeAdapterHorizontal = PlaceAdapter(DataList.LokasiDum)

        // Menampilkan RecyclerView
        showRecyclerList()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = hotelAdapterGrid

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = placeAdapterHorizontal

        // Menetapkan aksi ketika item di RecyclerView diklik
        hotelAdapterGrid.setOnItemClickCallback(object : HotelAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: HotelsData) {
                showSelectedHotel(data)
            }
        })

        placeAdapterHorizontal.setOnItemClickCallback(object : PlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PlacesData) {
                showSelectedPlace(data)
            }
        })
    }

    private fun showSelectedPlace(data: PlacesData) {
        // Membuat intent untuk berpindah ke Detail
        val navigateToDetail = Intent(this, Detail::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.name)
        navigateToDetail.putExtra("lokasi", data.lokasi)
        navigateToDetail.putExtra("image", data.image)
        navigateToDetail.putExtra("description", data.description)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }
    // Fungsi untuk menampilkan detail pemain yang dipilih
    private fun showSelectedHotel(data: HotelsData) {
        // Membuat intent untuk berpindah ke Detail
        val navigateToDetail = Intent(this, Detail::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.nama)
        navigateToDetail.putExtra("description", data.deskripsi)
        navigateToDetail.putExtra("image", data.gambar)


        // Memulai activity baru
        startActivity(navigateToDetail)
    }

}