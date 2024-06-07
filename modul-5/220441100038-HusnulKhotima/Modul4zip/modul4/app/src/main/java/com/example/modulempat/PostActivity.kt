package com.example.modulempat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.modulempat.room.AppDatabase
import com.example.modulempat.room.PostDatabase
import com.example.modulempat.room.PostViewModel
import com.example.modulempat.room.PostViewModelFactory
import com.example.modulempat.utils.reduceFileImage
import com.example.modulempat.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class PostActivity : AppCompatActivity() {

    private var currentImageUri: Uri? = null
    private lateinit var postViewModel: PostViewModel
    private lateinit var vPostDesc: TextInputEditText
    private lateinit var vPostImage: ImageView
    private lateinit var vText_img: TextView



    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            // Menampilkan ImageView jika gambar berhasil dipilih
            vPostImage.visibility = View.VISIBLE
            // Menyimpan URI gambar yang dipilih
            currentImageUri = firstImage.uri
            // Menampilkan pesan bahwa gambar berhasil dimasukkan
            vText_img.setText("Gambar berhasil dimasukkan")

            // Menggunakan library Glide untuk menampilkan gambar yang dipilih
            Glide.with(vText_img)
                .load(firstImage.uri)
                .into(vPostImage)
        } else {
            // Menyembunyikan ImageView jika tidak ada gambar yang dipilih
            View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val factory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        vPostImage = findViewById(R.id.up_gambar)
        vPostDesc = findViewById(R.id.unggah_deskripsi)
        vText_img = findViewById(R.id.unggah_foto)

        onClick()
    }

    private fun onClick(){
        val openImagePicker = findViewById<TextInputEditText>(R.id.unggah_foto)
        openImagePicker.setOnClickListener {
            imagePickerLauncher.launch(
                ImagePickerConfig {
                    mode = ImagePickerMode.SINGLE
                    returnMode = ReturnMode.ALL
                    isFolderMode = true
                    folderTitle = "Galeri"
                    isShowCamera = false
                    imageTitle = "Tekan untuk memilih gambar"
                    doneButtonText = "Selesai"
                }
            )
        }
        val btnSavedPlayer = findViewById<MaterialButton>(R.id.tombol_posting)
        btnSavedPlayer.setOnClickListener {
            // Memvalidasi input dan menyimpan data jika valid
            if (validateInput()) {
                savedData()
            }
        }

        val btnBack = findViewById<ImageButton>(R.id.back_button)
        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validateInput(): Boolean {
        var error = 0

        // Memeriksa apakah nama pemain kosong
        if (vPostDesc.text.toString().isEmpty()) {
            error++
            vPostDesc.error = "Nama pemain tidak boleh kosong"
        }


        // Memeriksa apakah gambar pemain kosong
        if (vText_img.text.toString().isEmpty()) {
            error++
            vText_img.error = "Gambar tidak boleh kosong"
        }

        // Mengembalikan true jika tidak ada error, false jika ada error
        return error == 0

    }

    private fun savedData() {
        // Mengubah URI gambar menjadi file dan mengurangi ukuran file
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        // Membuat objek pemain dengan data yang diinputkan
        val player = imageFile?.let {
            val descriptionText = vPostDesc.text.toString()
            val words = descriptionText.split(" ")
            val firstTwoWords = words.take(2).joinToString(" ")
            PostDatabase(
                id = 0,
//                name = firstTwoWords,
                description = descriptionText,
                image = imageFile,
                like = Random.nextInt(1, 51)
            )
        }

        // Menyimpan data pemain ke database
        if (player != null) postViewModel.insertPost(player)

        // Menampilkan pesan bahwa data pemain berhasil ditambahkan
        Toast.makeText(
            this@PostActivity,
            "Konten berhasil ditambahkan",
            Toast.LENGTH_SHORT
        ).show()

        // Mengakhiri activity
        finish()
    }
}