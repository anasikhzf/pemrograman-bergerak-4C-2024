package com.example.aplikasihotel


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasihotel.adapter.adapterLok
import com.example.aplikasihotel.adapter.adapterHotel
import com.example.aplikasihotel.data.listHotel
import com.example.aplikasihotel.data.hotel
import com.example.aplikasihotel.data.lok
import com.google.android.material.textview.MaterialTextView

class Home : AppCompatActivity() {
    // Deklarasi variabel untuk adapter dan RecyclerView
    private lateinit var locationAdapter: adapterLok
    private lateinit var hotelAdapter: adapterHotel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerviewHorizontal: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Mengambil data nama dari intent
        val getDataName = intent.getStringExtra("Nama")

        // Menampilkan teks sapaan dengan nama pengguna
        val displayTitle = findViewById<MaterialTextView>(R.id.materialTextView)
        displayTitle.text = "Hi, $getDataName"


        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.grid)
        recyclerviewHorizontal = findViewById(R.id.recyclerView)

        // Menginisialisasi semua adapter dengan data
        locationAdapter = adapterLok(listHotel.LokasiDum)
        hotelAdapter = adapterHotel(listHotel.HotelDum)


        // Menampilkan RecyclerView
        showRecyclerList()
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = hotelAdapter

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = locationAdapter

        // Menetapkan aksi ketika item di RecyclerView diklik
        locationAdapter.setOnItemClickCallback(object : adapterLok.OnItemClickCallback {
            override fun onItemClicked(data: lok) {
                showSelectedLocation(data)
            }
        })

        hotelAdapter.setOnItemClickCallback(object : adapterHotel.OnItemClickCallback {
            override fun onItemClicked(data: hotel) {
                showSelectedHotel(data)
            }
        })

    }

    // Fungsi untuk menampilkan detail hotel yang dipilih
    private fun showSelectedLocation(data: lok) {
//         Membuat intent untuk berpindah ke Detail
        val navigateToDetail = Intent(this, Detail::class.java)

        // Menambahkan dan membawa data ke intent dengan tujuan ke Detail
        navigateToDetail.putExtra("name", data.name)
        navigateToDetail.putExtra("lokasi", data.lokasi)
        navigateToDetail.putExtra("image", data.image)
        navigateToDetail.putExtra("description", data.description)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }

    // Fungsi untuk menampilkan detail yang dipilih
    private fun showSelectedHotel(data: hotel) {
        // Membuat intent untuk berpindah ke Detail
        val navigateToDetail = Intent(this, Detail::class.java)

        // Menambahkan dan membawa data hotel ke intent dengan tujuan ke Detail
        navigateToDetail.putExtra("name", data.nama)
        navigateToDetail.putExtra("description", data.deskripsi)
        navigateToDetail.putExtra("image", data.gambar)


        // Memulai activity baru
        startActivity(navigateToDetail)
    }

}