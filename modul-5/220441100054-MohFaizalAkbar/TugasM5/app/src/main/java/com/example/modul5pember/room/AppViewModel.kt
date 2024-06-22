package com.example.modul5pember.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * Kelas AppViewModel adalah kelas yang berfungsi sebagai ViewModel dalam arsitektur MVVM (Model-View-ViewModel).
 * Kelas ini memiliki konstruktor yang menerima AppRepository sebagai parameter.
 *
 * Fungsi insertPost(post: PostDatabase) digunakan untuk memasukkan data pemain ke dalam database.
 * Fungsi ini memanggil fungsi insertPost di AppRepository.
 *
 * Fungsi getAllPost() digunakan untuk mendapatkan semua data pemain dari database.
 * Fungsi ini memanggil fungsi getAllPost di AppRepository dan mengembalikan LiveData yang berisi daftar semua pemain.
 */
class AppViewModel(private val appRepository: AppRepository) : ViewModel() {

    // Fungsi insertPost digunakan untuk memasukkan data pemain ke dalam database.
    // Fungsi ini menerima parameter berupa objek PostEntity dan memanggil fungsi insertPost dari AppRepository.
    fun insertPost(post: PostDatabase) {
        appRepository.insertPost(post)
    }

    // Fungsi getAllPost digunakan untuk mendapatkan semua data pemain dari database.
    // Fungsi ini mengembalikan LiveData yang berisi daftar semua pemain.
    // LiveData adalah kelas dari Android Architecture Components yang memungkinkan kita untuk mengamati perubahan data dalam database dan secara otomatis memperbarui UI jika ada perubahan.
    fun getAllPost(): LiveData<List<PostDatabase>> {
        return appRepository.getAllPost()
    }

    // Fungsi deletePost digunakan untuk menghapus data pemain dari database.
    // Fungsi ini menerima parameter berupa objek PostEntity dan memanggil fungsi deletePost dari AppRepository.
    fun deletePost(post: PostDatabase) {
        appRepository.deletePost(post)
    }

    // Fungsi updatePost digunakan untuk memperbarui data pemain dalam database.
    // Fungsi ini menerima parameter berupa objek PostEntity dan memanggil fungsi updatePost dari AppRepository.
    fun updatePost(post: PostDatabase) {
        appRepository.updatePost(post)
    }
}