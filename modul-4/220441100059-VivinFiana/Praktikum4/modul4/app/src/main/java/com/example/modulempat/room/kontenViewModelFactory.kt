package com.example.modulempat.room

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modulempat.utils.DepedencyInjection

class kontenViewModelFactory private constructor(private val repoSitory: repoSitory) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")

    //memeriksa modelclass sudah sesuai dengan ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        //jika sesuai, instance baru dari kontenViewModel dibuat dengan menggunakan repoSitory dan dikembalikan
        if(modelClass.isAssignableFrom(kontenViewModel::class.java)){
            return kontenViewModel(repoSitory) as T
        }

        //jika tidak, metode ini melempar IllegalArgumentException
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    //memungkinkan pembuatan metode dan properti statis dalam Kotlin
    companion object{

        // dogunakan untuk memastikan perubahan nilai instance segera terlihat oleh thread lain.
        @Volatile
        private var instance: kontenViewModelFactory? = null

        //memeriksa instance sudah ada atau belum, jika belum menginisialisasi instance dengan melakukan sinkronisasi
        fun getInstance(context: Context): kontenViewModelFactory =
            instance ?: synchronized(this){

                //digunakan untuk mendapatkan instance dari repoSitory melalui mekanisme Dependency Injection
                instance ?: kontenViewModelFactory(DepedencyInjection.provideRepository(context))
            }.also { instance = it }
    }
}