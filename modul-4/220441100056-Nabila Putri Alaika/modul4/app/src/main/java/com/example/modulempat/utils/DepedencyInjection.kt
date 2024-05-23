package com.example.modulempat.utils

import android.content.Context
import com.example.modulempat.room.dataBase
import com.example.modulempat.room.kontenDatabase
import com.example.modulempat.room.repoSitory

object DepedencyInjection {
    fun provideRepository(context: Context): repoSitory {
        // Membuat instance dari AppDatabase
        val database = dataBase.getDatabase(context)
        // Membuat instance dari AppExecutors
        val appExecutors = AppExecutor()
        // Mendapatkan instance dari AppDao dari AppDatabase
        val dao = database.kontenDao()
        // Mendapatkan instance dari AppRepository menggunakan AppDao dan AppExecutors
        return repoSitory.getInstance(dao, appExecutors)
    }
}