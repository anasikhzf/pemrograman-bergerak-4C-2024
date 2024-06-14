package com.example.warover.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class playerViewModelFactory private constructor(private val repository: repository) :
    ViewModelProvider.NewInstanceFactory() {

    // Fungsi ini digunakan untuk membuat instance dari ViewModel
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Jika modelClass adalah atau merupakan subclass dari ExampleViewModel
        if (modelClass.isAssignableFrom(playerViewModel::class.java)) {
            // Membuat dan mengembalikan instance dari ExampleViewModel
            return playerViewModel(repository) as T
        }
        // Jika modelClass bukan ExampleViewModel atau subclass dari ExampleViewModel, lemparkan pengecualian
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    // Objek companion yang digunakan untuk membuat instance dari ExampleViewModelFactory
    companion object {
        // Variabel instance digunakan untuk menyimpan instance dari ExampleViewModelFactory
        @Volatile
        private var instance: playerViewModelFactory? = null

        // Fungsi ini digunakan untuk mendapatkan instance dari ExampleViewModelFactory
        fun getInstance(): playerViewModelFactory =
        // Jika instance tidak null, kembalikan instance
            // Jika instance null, buat instance baru
            instance ?: synchronized(this) {
                instance
                    ?: playerViewModelFactory(repository())
            }.also { instance = it }
    }
}
