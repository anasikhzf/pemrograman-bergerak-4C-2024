package com.example.roomcrud.activity

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
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
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDateTime
import com.example.roomcrud.R
import com.example.roomcrud.room.PostDatabase
import com.example.roomcrud.room.RoomViewModelFactory
import com.example.roomcrud.utils.reduceFileImage
import com.example.roomcrud.utils.uriToFile


class AddPostActivity : AppCompatActivity() {
    private var currentImageUri: Uri? = null
    private lateinit var postImage: ImageView
    private lateinit var appViewModel: AppViewModel
    private lateinit var name: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var playerImageInput: TextInputEditText

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            postImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            playerImageInput.setText("Gambar berhasil dimasukkan")
            Glide.with(postImage).load(firstImage.uri).into(postImage)
        } else {
            postImage.visibility = View.GONE
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posting)

        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        postImage = findViewById(R.id.imagepost)
        name = findViewById(R.id.etName)
        username = findViewById(R.id.etUsername)
        description = findViewById(R.id.etCaption)
        playerImageInput = findViewById(R.id.imagepost)

        onClick()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun onClick() {
        val openImagePicker = findViewById<Button>(R.id.btnChangePhoto)
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

        val btnSavedPlayer = findViewById<Button>(R.id.post)
        btnSavedPlayer.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }
    }

    private fun validateInput(): Boolean {
        var error = 0

        if (name.text.toString().isEmpty()) {
            error++
            name.error = "Nama tidak boleh kosong"
        }
        if (username.text.toString().isEmpty()) {
            error++
            username.error = "Username tidak boleh kosong"
        }
        if (description.text.toString().isEmpty()) {
            error++
            description.error = "Deskripsi tidak boleh kosong"
        }
        if (currentImageUri == null) {
            error++
            playerImageInput.error = "Gambar tidak boleh kosong"
        }

        return error == 0
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val post = imageFile?.let {
            PostDatabase(
                name = name.text.toString(),
                username = username.text.toString(),
                description = description.text.toString(),
                waktu = LocalDateTime.now().toString(),
                image = it
            )
        }

        if (post != null) appViewModel.insertPost(post)

        Toast.makeText(this, "Data post berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        finish()
    }
}
