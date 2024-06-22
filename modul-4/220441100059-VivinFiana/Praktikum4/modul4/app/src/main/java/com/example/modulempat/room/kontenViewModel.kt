package com.example.modulempat.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class kontenViewModel(private val repoSitory: repoSitory) : ViewModel() {

    // memanggil metode insertKonten pada repoSitory untuk memasukkan data kontenDatabase ke dalam database
    fun insertKonten(konten: kontenDatabase){
        repoSitory.insertKonten(konten)   //mengirimkan objek kontenDatabase ke repositori untuk disimpan
    }

    // mengembalikan LiveData yang berisi daftar kontenDatabase dari repositori.
    fun getAllKonten(): LiveData<List<kontenDatabase>>{

        //mengambil semua data kontenDatabase dari repositori dan mengembalikannya sebagai LiveData
        return repoSitory.getAllKonten()
    }
}