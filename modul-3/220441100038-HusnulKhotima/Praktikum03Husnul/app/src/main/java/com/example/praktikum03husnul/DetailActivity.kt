package com.example.praktikum03husnul

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream


class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mengambil data nama, deskripsi, dan gambar dari intent
        val getDataName = intent.getStringExtra("name")
        val getDataHotelName = intent.getStringExtra("hotelname")
        val getDataDescription = intent.getStringExtra("description")
        val getDataImage = intent.getIntExtra("image", 0)

        // Menghubungkan variabel dengan komponen di layout
        val hotelName = findViewById<TextView>(R.id.hotel_name)
        val hotelDesc = findViewById<TextView>(R.id.hotel_description)
        val hotelImage = findViewById<ImageView>(R.id.hotel_image)

        // Menampilkan data pemain
        hotelName.text = getDataHotelName
        hotelDesc.text = getDataDescription
        hotelImage.setImageResource(getDataImage)

        // Mendapatkan referensi ke tombol bagikan
        val btnShare = findViewById<ImageView>(R.id.bagikan_btn)
        // Simpan gambar ke cache directory
        val imageUri = saveImageToCache(getDataImage)

        if (imageUri != null) {
            // Membuat intent untuk berbagi gambar dan teks
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, imageUri)
                putExtra(Intent.EXTRA_TEXT, "$getDataHotelName\n\n$getDataDescription")
                type = "image/*"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            // Menggunakan ClipData untuk mengatasi masalah dengan WhatsApp
            val clipData = ClipData.newUri(contentResolver, "Image", imageUri)
            sendIntent.clipData = clipData
            // Mendapatkan referensi ke dashboard
            val btnBack = findViewById<ImageView>(R.id.kembali_btn)

            // Menetapkan aksi ketika tombol bagikan diklik
            btnShare.setOnClickListener {

                // Membuat intent untuk berbagi teks
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hotel Name: $getDataHotelName")
                    type = "text/plain"
                }

                // Memeriksa apakah WhatsApp terinstal
                val whatsappInstalled =
                    isPackageInstalled("com.whatsapp") || isPackageInstalled("com.whatsapp.w4b")
                if (whatsappInstalled) {

                    // Jika WhatsApp terinstal, atur paket intent ke "com.whatsapp" dan mulai activity
                    sendIntent.setPackage("com.whatsapp")
                    startActivity(sendIntent)
                } else {

                    // Jika WhatsApp tidak terinstal, tampilkan pesan toast
                    Toast.makeText(this, "WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show()
                }
            }

            // Menetapkan aksi ketika tombol back diklik
            btnBack.setOnClickListener {
                // Membuat intent untuk berpindah ke MainActivity
                val intent = Intent(this, DashboardActivity::class.java)

                // Menambahkan dan membawa data username dan password ke intent dengan tujuan ke MainActivity
                intent.putExtra("name", getDataName)

                // Memulai activity baru
                startActivity(intent)
            }
        }
    }
        private fun isPackageInstalled(packageName: String): Boolean {
            return try {
                // Mencoba mendapatkan informasi paket
                packageManager.getPackageInfo(packageName, 0)
                Log.d("DetailActivity", "Package $packageName is installed.")
                true
            } catch (e: PackageManager.NameNotFoundException) {
                // Jika paket tidak ditemukan, kembalikan false
                Log.d("DetailActivity", "Package $packageName is NOT installed.")
                false
            }
        }


        // Fungsi untuk menyimpan gambar ke cache directory dan mendapatkan URI
        private fun saveImageToCache(imageResId: Int): Uri? {
            return try {
                val drawable = resources.getDrawable(imageResId, null)
                val bitmap = (drawable as BitmapDrawable).bitmap
                val cachePath = File(cacheDir, "images")
                cachePath.mkdirs()
                val file = File(cachePath, "shared_image.png")
                val fileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
                fileOutputStream.close()
                FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileprovider",
                    file
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
