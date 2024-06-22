package com.example.modul3praktikum.adapter

import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.modul3praktikum.LinearActivity
import com.example.modul3praktikum.MainActivity
import com.example.modul3praktikum.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // back

        // Mengambil data nama, deskripsi, dan gambar dari intent
        val getDataName = intent.getStringExtra("name")
        val getDataDescription = intent.getStringExtra("desc")
        val getDataImage = intent.getIntExtra("image", 0)
        val imageId = intent.getIntExtra("image", 0)
        // Membuat bitmap dari gambar

        // Menghubungkan variabel dengan komponen di layout
        val placeName = findViewById<MaterialTextView>(R.id.placeName)
        val placeDescription = findViewById<MaterialTextView>(R.id.descpicture)
        val placeImage = findViewById<ShapeableImageView>(R.id.picture)
        // Menampilkan data pemain
        placeName.text = getDataName
        placeDescription.text = getDataDescription
        placeImage.setImageResource(getDataImage)
        // Mendapatkan referensi ke tombol bagikan
        val btnShare = findViewById<ImageButton>(R.id.bagikanbtn)

        // Menetapkan aksi ketika tombol bagikan diklik
        btnShare.setOnClickListener {

// Membuat pesan yang akan dibagikan
            val bmp = BitmapFactory.decodeResource(resources, imageId)
            val message = "Hai Saya *Nasikh* sedang melihat tempat wisata\n*${getDataName}* di Indonesia. Kamu harus melihatnya juga! Berikut adalah deskripsinya: \n${getDataDescription}"

// Membuat intent untuk membagikan pesan
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                val path = MediaStore.Images.Media.insertImage(contentResolver, bmp, "title", null)
                val uri = Uri.parse(path)
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/*"
                putExtra(Intent.EXTRA_TEXT, message)
            }

// Memulai intent untuk membagikan pesan
            startActivity(Intent.createChooser(shareIntent, "Bagikan ke:"))


        }

    }

}