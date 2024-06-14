package com.example.modul5pember

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.modul5pember.room.AppViewModel
import com.example.modul5pember.room.PostDatabase
import com.example.modul5pember.room.RoomViewModelFactory
import com.example.modul5pember.utils.reduceFileImage
import com.example.modul5pember.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.File

class UpdatePost : AppCompatActivity() {
    // Mendeklarasikan variabel untuk menyimpan URI gambar saat ini dan foto lama.
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null

    // Mendeklarasikan ViewModel untuk interaksi dengan database dan komponen UI lainnya.
    private lateinit var appViewModel: AppViewModel
    private lateinit var postImage: ImageView
    private lateinit var postDeskripsi: TextInputEditText
    private lateinit var postImageInput: TextInputEditText
    private lateinit var getData: PostDatabase

    // Mendeklarasikan imagePickerLauncher untuk memilih gambar dari galeri atau kamera.
    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            postImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri

            // Menggunakan Glide untuk memuat gambar ke ImageView.
            Glide.with(postImage)
                .load(firstImage.uri)
                .into(postImage)
        } else {
            View.GONE
        }
    }

    // Fungsi onCreate dipanggil ketika activity dibuat.
    // Fungsi ini digunakan untuk melakukan inisialisasi awal untuk activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_post)

        // Mendapatkan data pemain dari intent.
        getData = intent.getParcelableExtra("post")!!

        // Mendapatkan instance ViewModel.
        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        // Menghubungkan variabel dengan komponen di layout.
        postImage = findViewById(R.id.post_image)
        postDeskripsi = findViewById(R.id.post_desc_edit)
        postImageInput = findViewById(R.id.post_image_edit)

        // Mengatur teks untuk setiap komponen TextInputEditText.
        postDeskripsi.setText(getData.deskripsi)
        postImageInput.setText("Gambar berhasil ditambahkan")

        // Mendapatkan foto lama dari data pemain.
        oldPhoto = getData.image

        // Menggunakan Glide untuk memuat gambar ke ImageView.
        Glide.with(postImage)
            .load(getData.image)
            .into(postImage)

        // Menangani aksi klik.
        onClick()
    }

    // Fungsi onClick digunakan untuk menangani aksi klik pada tombol dan TextInputEditText.
    private fun onClick() {
        val btnPost = findViewById<MaterialButton>(R.id.saved_post)
        btnPost.setOnClickListener {
            // Jika semua input valid, simpan data.
            if (validateInput()) {
                savedData()
            }
        }

        // Menangani aksi klik pada TextInputEditText untuk membuka image picker.
        val openImagePicker = findViewById<TextInputEditText>(R.id.post_image_edit)
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
    }

    // Fungsi validateInput digunakan untuk memvalidasi input dari pengguna.
    private fun validateInput(): Boolean {
        var error = 0

        // Jika input kosong, tambahkan pesan error.
        if (postDeskripsi.text.toString().isEmpty()) {
            error++
            postDeskripsi.error = "Deskripsi postingan tidak boleh kosong"
        }

        if (postImageInput.text.toString().isEmpty()) {
            error++
            postImageInput.error = "Gambar tidak boleh kosong"
        }

        // Jika tidak ada error, kembalikan true. Jika ada, kembalikan false.
        return error == 0
    }

    // Fungsi savedData digunakan untuk menyimpan data pemain yang diperbarui ke database.
    private fun savedData() {
        // Mengurangi ukuran file gambar.
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        // Membuat objek PostDatabase baru dengan data yang diperbarui.
        val post = (if (currentImageUri != null) imageFile else oldPhoto)?.let {
            PostDatabase(
                id = getData.id,
                deskripsi = postDeskripsi.text.toString(),
                image = it
            )
        }

        // Memperbarui data pemain di database.
        if (post != null) appViewModel.updatePost(post)

        // Menampilkan pesan bahwa data pemain berhasil diubah.
        Toast.makeText(
            this@UpdatePost,
            "Postingan berhasil diubah",
            Toast.LENGTH_SHORT
        ).show()

        // Menutup activity.
        finish()
    }
}