package com.example.warover.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.warover.remote.repository


class playerViewModel (private val repository: repository) : ViewModel() {
    // Mendeklarasikan variabel listPlayer yang berisi LiveData dari List APIResponse dari repository
    val listPlayer: LiveData<List<PlayerData>> = repository.listPlayer

    // Mendeklarasikan variabel isLoading yang berisi LiveData dari Boolean (status loading) dari repository
    val isLoading: LiveData<Boolean> = repository.isLoading

    // Fungsi untuk mendapatkan semua pemain dari repository
    fun getAllPlayer() {
        repository.getAllPlayer()
    }
}