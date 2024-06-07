package com.example.modulempat.utils

import android.content.Context
import com.example.modulempat.room.AppDatabase
import com.example.modulempat.room.PostRepository

object DependencyInjection {
    fun provideRepository(context: Context): PostRepository {
        val database = AppDatabase.getDatabase(context)
        val appExecutors = AppExecutor()
        val dao = database.postDao()
        return PostRepository.getInstance(dao, appExecutors)
    }
}