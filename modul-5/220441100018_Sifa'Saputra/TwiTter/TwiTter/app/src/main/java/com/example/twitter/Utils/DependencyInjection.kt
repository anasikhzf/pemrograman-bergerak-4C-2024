package com.example.twitter.Utils

import android.content.Context
import com.example.twitter.Room.AppDatabase
import com.example.twitter.Room.ItemRepository

object DependencyInjection {
    fun provideRepository(context: Context): ItemRepository {
        val database = AppDatabase.getDatabase(context)
        val appExecutors = AppExecutors()
        val dao = database.itemDao()
        return ItemRepository.getInstance(dao, appExecutors)
    }
}