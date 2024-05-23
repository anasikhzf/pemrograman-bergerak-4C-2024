package com.example.modulempat.data

//digunakan untuk mendefinisikan kelas data di Kotlin
data class kontenData(
    val id: Int,  //menyimpan id
    val deskripsi: String,
    val image: Int,
    val like: Int,
    val komen: Int
)
