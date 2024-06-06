package com.example.twitter.Room

import androidx.lifecycle.LiveData
import com.example.twitter.Utils.AppExecutors

class ItemRepository private constructor(private val itemDao: ItemDao, private val appExecutors: AppExecutors) {

    fun getAllItem(): LiveData<List<ItemDatabase>> = itemDao.getAllItem()

    fun deleteItem(item: ItemDatabase) {
        appExecutors.diskIO().execute { itemDao.deletePost(item) }
    }

    fun insertItem(item: ItemDatabase) {
        appExecutors.diskIO().execute { itemDao.insertItem(item) }
    }

    fun updateItem(item: ItemDatabase) {
        appExecutors.diskIO().execute { itemDao.updateItem(item) }
    }

    companion object {
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(
            itemDao: ItemDao,
            appExecutors: AppExecutors
        ): ItemRepository = instance ?: synchronized(this) {
            instance ?: ItemRepository(itemDao, appExecutors)
        }.also { instance = it }
    }
}
