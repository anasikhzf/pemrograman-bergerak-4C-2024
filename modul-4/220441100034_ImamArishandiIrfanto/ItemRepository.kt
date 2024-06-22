package com.example.postanger.Room

import androidx.lifecycle.LiveData
import com.example.postanger.Utils.AppExecutors

class ItemRepository private constructor(private val itemDao: ItemDao, private val appExecutors: AppExecutors) {

    fun getAllItem(): LiveData<List<ItemDatabase>> = itemDao.getAllItem()

    fun insertItem(item: ItemDatabase) {
        appExecutors.diskIO().execute { itemDao.insertItem(item) }
    }

    fun deleteItem(item: ItemDatabase) {
        appExecutors.diskIO().execute { itemDao.deletePost(item) }
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