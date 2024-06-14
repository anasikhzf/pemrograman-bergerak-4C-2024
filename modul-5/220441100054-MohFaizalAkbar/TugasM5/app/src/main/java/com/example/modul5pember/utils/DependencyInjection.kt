package com.example.modul5pember.utils

import android.content.Context
import com.example.modul5pember.room.AppDatabase
import com.example.modul5pember.room.AppRepository

/**
 * Objek DependencyInjection berfungsi untuk menyediakan dependensi yang dibutuhkan dalam aplikasi.
 * Dalam hal ini, objek ini menyediakan AppRepository.
 *
 * Fungsi provideRepository(context: Context) digunakan untuk menyediakan instance dari AppRepository.
 * Fungsi ini pertama-tama membuat instance dari AppDatabase dan AppExecutors.
 * Kemudian, fungsi ini mendapatkan instance dari AppDao dari AppDatabase.
 * Akhirnya, fungsi ini mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors dan mengembalikannya.
 */

object DependencyInjection {
    // Menyediakan instance dari AppRepository
    fun provideRepository(context: Context): AppRepository {
        // Membuat instance dari AppDatabase
        val database = AppDatabase.getDatabase(context)
        // Membuat instance dari AppExecutors
        val appExecutors = AppExecutors()
        // Mendapatkan instance dari AppDao dari AppDatabase
        val dao = database.appDao()
        // Mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors
        return AppRepository.getInstance(dao, appExecutors)
    }
}