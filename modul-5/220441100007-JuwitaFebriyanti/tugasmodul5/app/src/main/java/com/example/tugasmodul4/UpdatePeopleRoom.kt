package com.example.tugasmodul4

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.tugasmodul4.room.PeopleEntity
import com.example.tugasmodul4.room.PeopleViewModel
import com.example.tugasmodul4.room.PeopleViewModelFactory
import com.example.tugasmodul4.utils.reduceFileImage
import com.example.tugasmodul4.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.File

class UpdatePeopleRoom : AppCompatActivity() {
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null
    private lateinit var playerImage: ImageView
    private lateinit var peopleViewModel: PeopleViewModel
    private lateinit var playerName: TextInputEditText
    private lateinit var playerDescription: TextInputEditText
    private lateinit var playerImageInput: TextInputEditText
    private lateinit var getDataPeople: PeopleEntity

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            playerImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            Glide.with(playerImage)
                .load(firstImage.uri)
                .into(playerImage)
        } else {
            playerImage.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_people_room)

        getDataPeople = intent.getParcelableExtra("people")!!

        val factory = PeopleViewModelFactory.getInstance(this)
        peopleViewModel = ViewModelProvider(this, factory)[PeopleViewModel::class.java]

        playerImage = findViewById(R.id.player_image)
        playerName = findViewById(R.id.player_name_edit)
        playerDescription = findViewById(R.id.player_desc_edit)
        playerImageInput = findViewById(R.id.people_image)

        playerName.setText(getDataPeople!!.name)
        playerDescription.setText(getDataPeople!!.description)
        Glide.with(this)
            .load(getDataPeople.image)
            .into(playerImage)

        oldPhoto = getDataPeople.image

        onClick()
    }

    private fun onClick() {
        val savedBtn = findViewById<MaterialButton>(R.id.saved_player)
        savedBtn.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }

        val openImagePicker = findViewById<TextInputEditText>(R.id.people_image)
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

    private fun validateInput(): Boolean {
        var error = 0

        if (playerName.text.toString().isEmpty()) {
            error++
            playerName.error = "Nama pemain tidak boleh kosong"
        }

        if (playerDescription.text.toString().isEmpty()) {
            error++
            playerDescription.error = "Deskripsi pemain tidak boleh kosong"
        }

        if (playerImageInput.text.toString().isEmpty()) {
            error++
            playerImageInput.error = "Gambar tidak boleh kosong"
        }

        return error == 0
    }

    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val people = (if (currentImageUri != null) imageFile else oldPhoto)?.let {
            PeopleEntity(
                id = getDataPeople.id,
                name = playerName.text.toString(),
                description = playerDescription.text.toString(),
                image = it,
                likeCount = 0,
                komen = 0
            )
        }

        Log.d("people", people.toString())

        if (people != null) peopleViewModel.updatePeople(people)

        Toast.makeText(
            this@UpdatePeopleRoom,
            "Data berhasil diubah",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}