package com.example.modulempat.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class kontenViewModel(private val repoSitory: repoSitory) : ViewModel() {

    //Memasukkan data pemain ke dalam database
    fun insertKonten(konten: kontenDatabase){
        repoSitory.insertKonten(konten)
    }

    //Mendapatkan semua data pemain dari database
    fun getAllKonten(): LiveData<List<kontenDatabase>>{
        return repoSitory.getAllKonten()
    }
}