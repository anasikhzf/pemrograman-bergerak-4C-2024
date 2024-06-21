package com.example.modulenam

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.example.modulenam.retrofit.Player
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
        window.statusBarColor = Color.TRANSPARENT

        // Mendapatkan data makanan dari intent
        val getData = intent.getParcelableExtra<Player>("players")!!

        // Menghubungkan variabel dengan komponen di layout
        val nama = findViewById<TextView>(R.id.namadet)
        val nom = findViewById<TextView>(R.id.nodet)
        val logo = findViewById<ImageView>(R.id.logodet)
        val foto = findViewById<ImageView>(R.id.fotodet)
        var back = findViewById<ConstraintLayout>(R.id.backdet)


        val gol = findViewById<TextView>(R.id.goldet)
        val asis = findViewById<TextView>(R.id.posisidet)
        val ratings = findViewById<TextView>(R.id.ratedet)
        val pos = findViewById<TextView>(R.id.posisidet)
        val des = findViewById<TextView>(R.id.desk)

        // Mengatur teks untuk nama dan deskripsi
        nama.text = getData.name
        nom.text = "#" + getData.number.toString()
        // Menggunakan library Glide untuk memuat gambar dari URL dan menampilkannya di ImageView
        Glide.with(this)
            .load(getData.club_logo)
            .into(logo)
        Glide.with(this)
            .load(getData.player_image)
            .into(foto)


        Glide.with(this)
            .load(getData.background_card) // replace with your image URL
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in
                Drawable>?) {
                    back.background = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // handle cleanup here
                }
            })

        gol.text = getData.goals.toString()
        asis.text = getData.assists.toString()
        ratings.text = getData.rating.toString()
        pos.text = getData.position
        des.text = getData.description

        onClick()
    }

    private fun onClick(){
        val btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}