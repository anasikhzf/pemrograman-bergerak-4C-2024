package com.example.modul5pember

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul5pember.adapter.MainAdapterRoom
import com.example.modul5pember.room.AppViewModel
import com.example.modul5pember.room.PostDatabase
import com.example.modul5pember.room.RoomViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // Mendeklarasikan ViewModel untuk interaksi dengan database
    private lateinit var appViewModel: AppViewModel
    // Mendeklarasikan adapter untuk RecyclerView
    private lateinit var mainAdapterRoom: MainAdapterRoom
    // Mendeklarasikan RecyclerView untuk menampilkan daftar pemain
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mendapatkan instance ViewModel
        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Mengamati perubahan data pemain dan memperbarui RecyclerView
        appViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                mainAdapterRoom = MainAdapterRoom(postData)
                recyclerView.adapter = mainAdapterRoom

                // Menangani aksi klik pada item di RecyclerView.
                mainAdapterRoom.setOnItemClickCallback(object :
                    MainAdapterRoom.OnItemClickCallback {
                    override fun onMoreClicked(data: PostDatabase, position: Int) {
                        PopUpFragment(data, position).show(supportFragmentManager, PopUpFragment.TAG)
                    }
                })
            }
        }

        // Menangani aksi klik pada tombol tambah pemain
        val btnAdd = findViewById<FloatingActionButton>(R.id.btn_add_post)
        btnAdd.setOnClickListener {
            val intent = Intent(this, TambahPost::class.java)
            startActivity(intent)
        }
    }

    // Fungsi yang dipanggil ketika activity di-restart
    override fun onRestart() {
        super.onRestart()

        // Memperbarui daftar pemain
        appViewModel.getAllPost()
    }
}