package com.example.modulenam.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class APIViewModel(private val repository: Repository) : ViewModel() {
    // Mendeklarasikan variabel listPlayer yang berisi LiveData dari List ExampleAPIResponse dari repository
    val listPlayer: LiveData<List<Player>> = repository.listPlayer

    // Mendeklarasikan variabel isLoading yang berisi LiveData dari Boolean (status loading) dari repository
    val isLoading: LiveData<Boolean> = repository.isLoading

    // Fungsi untuk mendapatkan semua pemain dari repository
    fun getAllPlayer() {
        repository.getAllPlayer()
    }
}