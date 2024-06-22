package com.example.roomcrud

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.roomcrud.room.AppViewModel
import com.example.roomcrud.room.PostDatabase
import com.example.roomcrud.room.RoomViewModelFactory
import com.example.roomcrud.utils.reduceFileImage
import com.example.roomcrud.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AddPost : AppCompatActivity(){
    private var currentImageUri: Uri? = null
    private lateinit var playerImage: ImageView
    private lateinit var appViewModel: AppViewModel
    private lateinit var playerName: TextInputEditText
    private lateinit var playerDescription: TextInputEditText
    private lateinit var playerImageInput: ImageView
    private lateinit var playerUsername: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posting)
        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        playerImage = findViewById(R.id.imagepost)
        playerName = findViewById(R.id.etName)
        playerUsername = findViewById(R.id.etUsername)
        playerDescription = findViewById(R.id.etCaption)

        onClick()

        val back = findViewById<ImageButton>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClick(){
        val openImagePicker = findViewById<MaterialTextView>(R.id.btnChangePhoto)
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
        val btnSavedPlayer = findViewById<MaterialButton>(R.id.post)
        btnSavedPlayer.setOnClickListener {
            if (validateInput()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    savedData()
                } else {
                    Toast.makeText(this, "Fitur ini membutuhkan Android 10 atau lebih tinggi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        var error = 0

        if (playerName.text.toString().isEmpty()) {
            error++
            playerName.error = "Nama tidak boleh kosong"
        }
        if (playerUsername.text.toString().isEmpty()) {
            error++
            playerUsername.error = "Username tidak boleh kosong"
        }
        if (playerDescription.text.toString().isEmpty()) {
            error++
            playerDescription.error = "Deskripsi tidak boleh kosong"
        }
        if (currentImageUri == null) {
            error++
            Toast.makeText(this, "Gambar tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }

        return error == 0
    }

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            playerImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            val cekgambar = findViewById<MaterialTextView>(R.id.btnChangePhoto)
            cekgambar.setText("Ganti Foto")
            Glide.with(playerImage)
                .load(firstImage.uri)
                .into(playerImage)
        } else {
            View.GONE
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }
        val TanggalUnggah = LocalDate.now()
        val jam = LocalTime.now().withSecond(0).withNano(0)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val waktuUnggah = "$TanggalUnggah ${jam.format(formatter)}"

        val player = imageFile?.let {
            PostDatabase(
                name = playerName.text.toString(),
                username = "@"+playerUsername.text.toString(),
                description = playerDescription.text.toString(),
                likes = 0,
                waktu = waktuUnggah,
                image = imageFile
            )
        }

        if (player != null) appViewModel.insertPost(post = player)

        Toast.makeText(
            this@AddPost,
            "Postingan Berhasil Diunggah",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}